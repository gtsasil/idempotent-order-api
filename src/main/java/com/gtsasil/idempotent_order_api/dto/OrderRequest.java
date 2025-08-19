package com.gtsasil.idempotent_order_api.dto;

import java.math.BigDecimal;

public class OrderRequest {

    private String externalId;

    private BigDecimal amount;

    public OrderRequest() {
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}