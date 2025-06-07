package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.enums.CouponStatus;
import com.example.tomatomall.po.Account;
import com.example.tomatomall.po.Coupon;
import com.example.tomatomall.repository.AccountRepository;
import com.example.tomatomall.repository.CouponRepository;
import com.example.tomatomall.service.CouponService;
import com.example.tomatomall.vo.CouponVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponRepository couponRepository;

    @Resource
    private AccountRepository accountRepository;

    @Override
    public boolean issueCoupon(CouponVO couponVO) {
        try {
            Coupon coupon = new Coupon();
            coupon.setAmount(couponVO.getAmount());
            coupon.setQuantity(couponVO.getQuantity());
            coupon.setStatus(CouponStatus.AVAILABLE);
            coupon.setStartTime(couponVO.getStartTime());
            coupon.setEndTime(couponVO.getEndTime());
            coupon.setMinAmount(couponVO.getMinAmount());
            // 发放时不设置用户，等待用户领取
            coupon.setAccount(null);
            
            couponRepository.save(coupon);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean claimCoupon(Integer couponId, Integer userId) {
        try {
            Optional<Coupon> couponOpt = couponRepository.findById(couponId);
            if (!couponOpt.isPresent()) {
                return false;
            }
            
            Coupon coupon = couponOpt.get();
            
            // 检查优惠券是否可领取
            if (coupon.getAccount() != null || coupon.getQuantity() <= 0 || 
                coupon.getStatus() != CouponStatus.AVAILABLE) {
                return false;
            }
            
            // 检查是否过期
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (now.after(coupon.getEndTime())) {
                coupon.setStatus(CouponStatus.EXPIRED);
                couponRepository.save(coupon);
                return false;
            }
            
            // 查找用户
            Optional<Account> accountOpt = accountRepository.findById(userId);
            if (!accountOpt.isPresent()) {
                return false;
            }
            
            // 领取优惠券
            coupon.setAccount(accountOpt.get());
            coupon.setQuantity(coupon.getQuantity() - 1);
            
            couponRepository.save(coupon);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CouponVO> getUserCoupons(Integer userId) {
        Optional<Account> accountOpt = accountRepository.findById(userId);
        if (!accountOpt.isPresent()) {
            return null;
        }
        
        Account account = accountOpt.get();
        List<Coupon> coupons = couponRepository.findByAccount(account);
        
        // 更新过期状态
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (Coupon coupon : coupons) {
            if (now.after(coupon.getEndTime()) && coupon.getStatus() == CouponStatus.AVAILABLE) {
                coupon.setStatus(CouponStatus.EXPIRED);
                couponRepository.save(coupon);
            }
        }
        
        return coupons.stream().map(Coupon::toVO).collect(Collectors.toList());
    }

    @Override
    public boolean useCoupon(Integer couponId, BigDecimal orderAmount) {
        try {
            Optional<Coupon> couponOpt = couponRepository.findById(couponId);
            if (!couponOpt.isPresent()) {
                return false;
            }
            
            Coupon coupon = couponOpt.get();
            
            // 检查优惠券状态
            if (coupon.getStatus() != CouponStatus.AVAILABLE) {
                return false;
            }
            
            // 检查是否过期
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (now.after(coupon.getEndTime())) {
                coupon.setStatus(CouponStatus.EXPIRED);
                couponRepository.save(coupon);
                return false;
            }
            
            // 检查订单金额是否满足最小使用条件
            if (orderAmount.compareTo(coupon.getMinAmount()) < 0) {
                return false;
            }
            
            // 使用优惠券
            coupon.setStatus(CouponStatus.UNAVAILABLE);
            couponRepository.save(coupon);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CouponVO> getAvailableCoupons() {
        List<Coupon> coupons = couponRepository.findAvailableForClaim();
        
        // 过滤掉过期的优惠券
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return coupons.stream()
                .filter(coupon -> now.before(coupon.getEndTime()))
                .map(Coupon::toVO)
                .collect(Collectors.toList());
    }
} 