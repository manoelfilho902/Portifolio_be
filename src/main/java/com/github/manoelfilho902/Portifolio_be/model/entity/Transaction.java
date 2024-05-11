package com.github.manoelfilho902.Portifolio_be.model.entity;

import com.github.manoelfilho902.Portifolio_be.model.emunerate.TransactionStatus;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.TransactionType;
import com.github.manoelfilho902.Portifolio_be.model.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Entity(name = "transaction_entity")
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "value_column")
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDate creation_date;
    private LocalDate due_date;
    @Column(columnDefinition = "text")
    private String obs;
    @Column(name = "status_column") // to manage indetifier errors
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client_id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user_id;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

}
