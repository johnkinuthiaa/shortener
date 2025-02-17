package com.slippery.shortener.service;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.UrlModel;

public interface UrlService {
    UrlDto shorten(UrlModel urlModel,Long userId);
    UrlDto findAll();
    UrlDto calculateAllClicksForUser(Long userId);

    UrlDto findAllByUser(Long userId);
    UrlDto deleteById(Long urlId,Long userId);
    UrlDto getOriginal(String shortenedUrl);
}
