package com.example.tomatomall.repository;

import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Coupon;
import com.example.tomatomall.enums.CouponStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByAccount(Account account);
    
    List<Coupon> findByAccountAndStatus(Account account, CouponStatus status);
    
    @Query("SELECT c FROM Coupon c WHERE c.account IS NULL AND c.quantity > 0")
    List<Coupon> findAvailableForClaim();
} 