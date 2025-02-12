package com.slippery.shortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.shortener.models.QrCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BarcodeDto {
    private String message;
    private int id;
    private QrCode qrCode;
    private List<QrCode> qrCodeList;
    private BufferedImage image;
}
