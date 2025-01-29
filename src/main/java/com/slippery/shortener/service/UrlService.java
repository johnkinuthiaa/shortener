package com.slippery.shortener.service;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.UrlModel;

public interface UrlService {
    UrlDto shorten(UrlModel urlModel);
    UrlDto getOriginal(String shortenedUrl);
}
