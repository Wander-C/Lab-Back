package com.example.tomatomall.repository;

import com.example.tomatomall.po.Stockpile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockpileRepository extends JpaRepository<Stockpile,Integer> {
    Stockpile findByProductId(Integer ProductId);
}
