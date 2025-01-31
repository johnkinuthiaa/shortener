package com.slippery.shortener.service.impl;

import com.slippery.shortener.dto.BarcodeDto;
import com.slippery.shortener.models.BarCode;
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
    public BarcodeDto createBarcode(BarCode barCode) {
        BarcodeDto response =new BarcodeDto();
        EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barCode.getUrlFor());
        barCode.setImage(canvas.getBufferedImage().toString().getBytes());
        barCode.setContentType(String.valueOf(canvas.getBufferedImage().getType()));
        repository.save(barCode);
        response.setImage(canvas.getBufferedImage());
        return response;
    }

    @Override
    public BarcodeDto getAll() {
        return null;
    }
}
