package com.slippery.shortener.service.impl;

import com.slippery.shortener.dto.BarcodeDto;
import com.slippery.shortener.models.QrCode;
import com.slippery.shortener.repository.BarCodeRepository;
import com.slippery.shortener.service.BarCodeService;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class BarcodeServiceImpl implements BarCodeService {
    private final BarCodeRepository repository;

    public BarcodeServiceImpl(BarCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public BarcodeDto createBarcode(QrCode qrCode) {
        BarcodeDto response =new BarcodeDto();

        repository.save(qrCode);
        return response;
    }

    @Override
    public BarcodeDto getAll() {
        return null;
    }
}
