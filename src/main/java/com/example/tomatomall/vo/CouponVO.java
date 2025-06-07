package com.example.tomatomall.vo;

import com.example.tomatomall.enums.CouponStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CouponVO {
    private Integer id;
    private BigDecimal amount;
    private Integer quantity;
    private CouponStatus status;
    private Integer accountId;
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal minAmount;
} 