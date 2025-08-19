package com.gtsasil.idempotent_order_api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gtsasil.idempotent_order_api.dto.OrderRequest;
import com.gtsasil.idempotent_order_api.model.Order;
import com.gtsasil.idempotent_order_api.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional
    public Order createOrder(OrderRequest request) {

        Optional<Order> existingOrder = orderRepository.findByExternalId(request.getExternalId());

        if (existingOrder.isPresent()) {
            System.out.println(
                    "LOG: Pedido com externalId " + request.getExternalId() + " já existe. Retornando o existente.");
            return existingOrder.get();
        } else {

            System.out.println("LOG: Criando novo pedido com externalId: " + request.getExternalId());
            Order newOrder = new Order();
            newOrder.setExternalId(request.getExternalId());
            newOrder.setAmount(request.getAmount());
            newOrder.setStatus("PENDING");
            newOrder.setUpdatedAt(LocalDateTime.now()); 
            return orderRepository.save(newOrder);
        }
    }
}
