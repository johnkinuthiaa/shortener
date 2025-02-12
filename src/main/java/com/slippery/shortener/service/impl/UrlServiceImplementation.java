package com.slippery.shortener.service.impl;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.UrlModel;
import com.slippery.shortener.repository.UrlRepository;
import com.slippery.shortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImplementation implements UrlService {
    private final UrlRepository repository;

    public UrlServiceImplementation(UrlRepository repository) {
        this.repository = repository;

    }
    private String createShortUrl(){
        Random random =new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shrtn.");

        String[] chars ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","z","y","z"};
        for(int i= 0;i<15;i++){
            stringBuilder.append(chars[random.nextInt(20)]);
        }
        return stringBuilder.toString();

    }

    @Override
    public UrlDto shorten(UrlModel urlModel) {
        UrlDto response =new UrlDto();
        var shortUrl =createShortUrl();
        urlModel.setShortUrl(shortUrl);
        repository.save(urlModel);
        response.setMessage("Url created");
        response.setStatusCode(200);
        response.setUrlModel(urlModel);
        return response;
    }

    @Override
    public UrlDto findAll() {
        UrlDto response =new UrlDto();
        response.setUrlModels(repository.findAll());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UrlDto deleteById(Long urlId) {
        UrlDto response =new UrlDto();
        Optional<UrlModel> existingUrl =repository.findById(urlId);
        if(existingUrl.isEmpty()){
            response.setStatusCode(404);
            response.setMessage("Url not found");
            return response;
        }
        repository.deleteById(existingUrl.get().getId());
        response.setStatusCode(200);
        response.setMessage("Url deleted");
        return response;
    }

    @Override
    public UrlDto getOriginal(String shortenedUrl) {
        UrlDto response =new UrlDto();


        List<UrlModel> existingLink =repository.findAll().stream()
                .filter(urlModel -> urlModel.getShortUrl().equalsIgnoreCase(shortenedUrl))
                .toList();

        if(existingLink.isEmpty()){
            response.setMessage("No link found");
            response.setStatusCode(404);
            return response;
        }

        response.setOriginalUrl(existingLink.get(0).getOriginalUrl());
        response.setMessage("original url");
        response.setStatusCode(200);
        return response;
    }
}
