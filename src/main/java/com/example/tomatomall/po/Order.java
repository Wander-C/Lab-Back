package com.example.tomatomall.po;

import com.example.tomatomall.enums.OrderStatus;
import com.example.tomatomall.vo.OrderVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false, insertable = false, updatable = false)
    private Account account;
    @Column(name = "total_amount",nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(name = "payment_method",nullable = false)
    private String paymentMethod;
    @Column(name = "status",nullable = false)
    private OrderStatus status;
    @Column(name = "create_time",nullable = false)
    Timestamp createTime;

    public OrderVO toVO(){
        OrderVO orderVO = new OrderVO();
        orderVO.setId(this.id);
        orderVO.setAccountVO(this.account.toVO());
        orderVO.setTotalAmount(this.totalAmount);
        orderVO.setStatus(this.status);
        orderVO.setCreateTime(this.createTime);
        orderVO.setPaymentMethod(this.paymentMethod);
        return orderVO;
    }
}
