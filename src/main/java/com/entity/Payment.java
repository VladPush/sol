package com.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Entity(name = "payment")
@Data
public class Payment implements Persistable<UUID> {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    private long payerAccount;

    private long receiverAccount;

    private float amount;

    private String comment;

    @Override
    public boolean isNew() {
        return true;
    }
}
