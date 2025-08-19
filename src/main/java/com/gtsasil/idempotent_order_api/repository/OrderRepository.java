package com.gtsasil.idempotent_order_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtsasil.idempotent_order_api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    Optional<Order> findByExternalId(Object externalId);

}
