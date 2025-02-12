package com.slippery.shortener.repository;

import com.slippery.shortener.models.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarCodeRepository extends JpaRepository<QrCode,Long> {
}
