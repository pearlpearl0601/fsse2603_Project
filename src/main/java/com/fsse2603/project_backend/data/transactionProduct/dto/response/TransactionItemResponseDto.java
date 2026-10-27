package com.fsse2603.project_backend.data.transactionProduct.dto.response;

import com.fsse2603.project_backend.data.product.dto.response.ProductResponseDto;

import java.math.BigDecimal;

public class TransactionItemResponseDto {

    private Integer tpid;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal subtotal;

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
