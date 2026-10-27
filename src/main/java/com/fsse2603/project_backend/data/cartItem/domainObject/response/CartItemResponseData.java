package com.fsse2603.project_backend.data.cartItem.domainObject.response;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.user.domainObject.response.UserResponseData;

public class CartItemResponseData{
    private Integer cid;
    // ProductResponseData is a class, and in Java any class can be used as the type of a field, parameter, or return value.
    private ProductResponseData product;
    private UserResponseData user;
    private Integer quantity;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductResponseData getProduct() {
        return product;
    }

    public void setProduct(ProductResponseData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
