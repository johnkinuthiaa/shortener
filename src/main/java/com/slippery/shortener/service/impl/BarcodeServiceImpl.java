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
        EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, qrCode.getUrlFor());
        qrCode.setImage(canvas.getBufferedImage().toString().getBytes());
        qrCode.setContentType(String.valueOf(canvas.getBufferedImage().getType()));
        repository.save(qrCode);
        response.setImage(canvas.getBufferedImage());
        return response;
    }

    @Override
    public BarcodeDto getAll() {
        return null;
    }
}
