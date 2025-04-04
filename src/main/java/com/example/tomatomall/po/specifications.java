package com.example.tomatomall.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class specifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item",nullable = false)
    private String item;

    @Column(name = "value",nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id",updatable = false)
    private Products products;

}
