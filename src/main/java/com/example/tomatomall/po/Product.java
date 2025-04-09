package com.example.tomatomall.po;

import com.example.tomatomall.enums.Role;
import com.example.tomatomall.vo.AccountVO;
import com.example.tomatomall.vo.ProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @Column(name = "detail")
    private String detail;

    public ProductVO toVO(){
        ProductVO productVO = new ProductVO();
        productVO.setId(this.id);
        productVO.setTitle(this.title);
        productVO.setPrice(this.price);
        productVO.setRate(this.rate);
        productVO.setDescription(this.description);
        productVO.setCover(this.cover);
        productVO.setDetail(this.detail);
        return productVO;
    }
}
