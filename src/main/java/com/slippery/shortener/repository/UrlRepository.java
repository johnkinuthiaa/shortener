package com.slippery.shortener.repository;

import com.slippery.shortener.models.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlModel,Long> {
}
