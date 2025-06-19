package com.kb.shop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    public enum ShippingStatus {
        PENDING,       // 배송 준비 중
        SHIPPED,       // 배송 시작
        IN_TRANSIT,    // 배송 중
        DELIVERED,     // 배송 완료
        CANCELLED      // 배송 취소
    }

}
