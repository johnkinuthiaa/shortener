package com.slippery.shortener.controller;

import com.slippery.shortener.dto.BarcodeDto;
import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.QrCode;
import com.slippery.shortener.models.UrlModel;
import com.slippery.shortener.service.BarCodeService;
import com.slippery.shortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

@RequestMapping("/api/v1/shrtn")
@RestController
@CrossOrigin
public class UrlController {
    private final UrlService service;
    private final BarCodeService barCodeService;

    public UrlController(UrlService service, BarCodeService barCodeService) {
        this.service = service;
        this.barCodeService = barCodeService;
    }
    @PostMapping("/create")
    public ResponseEntity<UrlDto> createUrl(@RequestBody UrlModel url,@RequestParam Long userId) {
        return ResponseEntity.ok(service.shorten(url,userId));
    }
    @GetMapping("/get/original")
    public ResponseEntity<UrlDto> getOriginal(@RequestParam String shortenedUrl){
        return ResponseEntity.ok(service.getOriginal(shortenedUrl));
    }
    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlDto urlMapping =service.getOriginal(shortUrl);
        if (urlMapping != null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", urlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/get/all")
    public ResponseEntity<UrlDto> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<UrlDto> deleteById(@RequestParam Long urlId,@RequestParam Long userId){
        return ResponseEntity.ok(service.deleteById(urlId,userId));
    }
    @PostMapping("/barcode")
    public BarcodeDto createBarcode(@RequestBody QrCode qrCode){
        return barCodeService.createBarcode(qrCode);
    }
    @GetMapping("/{userId}/all-urls")
    public ResponseEntity<UrlDto> findAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(service.findAllByUser(userId));
    }
}
