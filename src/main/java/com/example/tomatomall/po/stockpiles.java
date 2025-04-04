package com.example.tomatomall.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class stockpiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id",updatable = false)
    private Products products;

    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "frozen",nullable = false)
    private Integer frozen;
}
