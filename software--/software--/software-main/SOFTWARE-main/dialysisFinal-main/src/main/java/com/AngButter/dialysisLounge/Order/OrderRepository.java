package com.AngButter.dialysisLounge.Order;

import com.AngButter.dialysisLounge.User.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 특정 사용자의 주문 내역 조회
    List<Order> findByUser(SiteUser user);
}
