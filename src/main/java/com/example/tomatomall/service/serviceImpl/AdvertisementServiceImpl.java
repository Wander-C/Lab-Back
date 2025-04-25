package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.exception.TomatoMallException;
import com.example.tomatomall.po.Advertisement;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.repository.AdvertisementRepository;
import com.example.tomatomall.service.AdvertisementService;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.AdvertisementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<AdvertisementVO> getAllAdvertisements() {

        return advertisementRepository.findAll().stream().map(Advertisement::toVO).collect(Collectors.toList());
    }

    @Override
    public AdvertisementVO getAdvertisementById(Integer id) {
        return advertisementRepository.findById(id).map(Advertisement::toVO).orElse(null);
    }

    @Override
    public AdvertisementVO createAdvertisement(AdvertisementVO advertisementVO) {
        System.out.println(advertisementVO.getProductId());
        Product product = productService.getProduct(advertisementVO.getProductId()).toPO();
        Advertisement advertisement = advertisementVO.toPO(productService);
        advertisement.setProduct(product);
        advertisementRepository.save(advertisement);
        return advertisement.toVO();
    }

    @Override
    public Boolean updateAdvertisement(AdvertisementVO advertisementVO) {
        Advertisement advertisement = advertisementRepository.findById(advertisementVO.getId()).orElse(null);
        Product product = productService.getProduct(advertisementVO.getProductId()).toPO();
        if (advertisement != null) {
            advertisement = advertisementVO.toPO(productService);
            advertisementRepository.save(advertisement);
        }else{
            throw TomatoMallException.productNotExists();
        }

        return true;
    }

    @Override
    public Boolean deleteAdvertisement(Integer id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);
        if (advertisement!= null) {
            advertisementRepository.delete(advertisement);
            return true;
        }
        return true;
    }
}
