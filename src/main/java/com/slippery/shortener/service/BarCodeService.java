package com.slippery.shortener.service;

import com.slippery.shortener.dto.BarcodeDto;
import com.slippery.shortener.models.BarCode;

public interface BarCodeService {
    BarcodeDto createBarcode(BarCode barCode);
    BarcodeDto getAll();
}
