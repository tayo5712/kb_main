package com.kb.shop.controller;

import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    // 배송 정보를 생성하는 POST 호출을 생성합니다. (path : /shipping)
    @PostMapping
    public ResponseEntity<String> createShippingInfo(@RequestBody ShippingInfo shippingInfo) {
        shippingService.createShippingInfo(shippingInfo);
        return ResponseEntity.ok("Shipping information created successfully.");
    }

    // 배송 Status를 변경하는 PUT 호출을 생성합니다. (path : /shipping)
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateShippingStatus(@PathVariable Long id,
                                                       @RequestParam ShippingInfo.ShippingStatus status) {
        shippingService.updateShippingStatus(id, status);
        return ResponseEntity.ok("Shipping status updated to: " + status);
    }

    // 배송 정보를 확인하는 GET 호출을 생성합니다.  (path : /shipping)
    @GetMapping("/{id}")
    public ResponseEntity<ShippingInfo> getShippingInfo(@PathVariable Long id) {
        ShippingInfo info = shippingService.getShippingInfoById(id);
        if (info != null) {
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
