package com.slippery.shortener.repository;

import com.slippery.shortener.models.Clicks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClicksRepository extends JpaRepository<Clicks,Long> {
}
