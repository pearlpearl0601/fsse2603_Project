package com.fsse2603.project_backend.data.transaction.entity;

import com.fsse2603.project_backend.data.transaction.status.TransactionStatus;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne   //refer to one user
    @JoinColumn(name = "buyer_uid", nullable= false)
    private UserEntity buyer;

    @Column(nullable= false)
    private LocalDateTime dateTime;

    @Column(nullable= false)
    private TransactionStatus status;

    @Column(nullable= false)
    private BigDecimal total;

    @OneToMany(mappedBy ="transaction", fetch = FetchType.EAGER)
    private List<TransactionProductEntity> transactionProductList = new ArrayList<>();

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductEntity> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProductEntity> transactionProductList) {
        this.transactionProductList = transactionProductList;
    }
}

