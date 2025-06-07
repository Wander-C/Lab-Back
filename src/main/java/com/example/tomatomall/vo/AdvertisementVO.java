package com.example.tomatomall.vo;

import com.example.tomatomall.po.Advertisement;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.service.ProductService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
@NoArgsConstructor
public class AdvertisementVO {




    private Integer id;
    private String title;
    private String content;
    private String imgUrl;
    private Integer[] productIds;

    public Advertisement toPO(ProductService productService){
        Advertisement advertisement = new Advertisement();
        advertisement.setId(this.id);
        advertisement.setTitle(this.title);
        advertisement.setContent(this.content);
        advertisement.setImageUrl(this.imgUrl);
        return advertisement;
    }
}
