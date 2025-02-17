package com.slippery.shortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.shortener.models.UrlModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlDto{
    private String message;
    private int statusCode;
    private UrlModel urlModel;
    private String originalUrl;
    private List<UrlModel> urlModels;
    private Long clicks;
}
