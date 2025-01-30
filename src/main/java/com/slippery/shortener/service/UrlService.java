package com.slippery.shortener.service;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.UrlModel;

public interface UrlService {
    UrlDto shorten(UrlModel urlModel);
    UrlDto findAll();
    UrlDto deleteById(Long urlId);
    UrlDto getOriginal(String shortenedUrl);
}
