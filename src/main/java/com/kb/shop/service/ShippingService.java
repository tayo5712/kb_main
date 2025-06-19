package com.kb.shop.service;

import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    // 배송 정보 등록
    public void createShippingInfo(ShippingInfo shippingInfo) {
        shippingRepository.insertShippingInfo(shippingInfo);
    }

    // 배송 정보 조회 (ID 기준)
    public ShippingInfo getShippingInfoById(Long id) {
        return shippingRepository.selectShippingInfoById(id);
    }

    // 특정 주문에 대한 배송 정보 전체 조회
    public List<ShippingInfo> getShippingInfosByOrderId(Long orderId) {
        return shippingRepository.selectShippingInfoByOrderId(orderId);
    }

    // 배송 상태 변경
    public void updateShippingStatus(Long shippingId, ShippingInfo.ShippingStatus status) {
        shippingRepository.updateShippingStatus(shippingId, status);
    }

    // 배송 정보 삭제
    public void deleteShippingInfo(Long id) {
        shippingRepository.deleteShippingInfo(id);
    }
}
