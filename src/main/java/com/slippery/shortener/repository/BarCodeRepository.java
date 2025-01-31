package com.slippery.shortener.repository;

import com.slippery.shortener.models.BarCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarCodeRepository extends JpaRepository<BarCode,Long> {
}
