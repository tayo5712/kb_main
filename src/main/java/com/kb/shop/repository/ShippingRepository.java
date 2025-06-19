package com.kb.shop.repository;

import com.kb.shop.domain.ShippingInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShippingRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShippingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 배송 정보 조회 (ID 기준)
    public ShippingInfo selectShippingInfoById(Long id) {
        String sql = "SELECT * FROM shipping_info WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(ShippingInfo.class));
    }

    // 특정 주문에 대한 배송 정보 전체 조회
    public List<ShippingInfo> selectShippingInfoByOrderId(Long orderId) {
        String sql = "SELECT * FROM shipping_info WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, BeanPropertyRowMapper.newInstance(ShippingInfo.class));
    }

    // 배송 정보 저장
    public void insertShippingInfo(ShippingInfo shippingInfo) {
        String sql = "INSERT INTO shipping_info (order_id, order_item_id, shipping_status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                shippingInfo.getOrder().getId(),
                shippingInfo.getOrderItem().getId(),
                shippingInfo.getShippingStatus().toString()
        );
    }

    // 배송 상태 업데이트
    public void updateShippingStatus(Long shippingId, ShippingInfo.ShippingStatus status) {
        String sql = "UPDATE shipping_info SET shipping_status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.toString(), shippingId);
    }

    // 배송 정보 삭제
    public void deleteShippingInfo(Long id) {
        String sql = "DELETE FROM shipping_info WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
