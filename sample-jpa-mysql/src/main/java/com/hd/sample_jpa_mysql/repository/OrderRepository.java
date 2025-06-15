package com.hd.sample_jpa_mysql.repository;

import com.hd.sample_jpa_mysql.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

