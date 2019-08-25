package com.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {

    @Query("SELECT p FROM payment p WHERE  p.amount = (SELECT MAX(amount) FROM payment)")
    Collection<Payment> getPaymentsWithMaxAmount();

}
