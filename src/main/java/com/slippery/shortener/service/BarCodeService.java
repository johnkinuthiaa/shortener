package com.slippery.shortener.service;

import com.slippery.shortener.dto.BarcodeDto;
import com.slippery.shortener.models.QrCode;

public interface BarCodeService {
    BarcodeDto createBarcode(QrCode qrCode);
    BarcodeDto getAll();
}
