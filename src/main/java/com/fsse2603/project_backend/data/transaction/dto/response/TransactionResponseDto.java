package com.fsse2603.project_backend.data.transaction.dto.response;

import com.fsse2603.project_backend.data.transactionProduct.dto.response.TransactionItemResponseDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {

    private Integer tid;
    private Integer buyerUid;
    private String datetime;
    private String status;
    private BigDecimal total;
    private List<TransactionItemResponseDto> items =
            new ArrayList<>();
    public List<TransactionItemResponseDto> getItems() {
        return items;
    }


    public void setItems(List<TransactionItemResponseDto> items) {
        this.items = items;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
