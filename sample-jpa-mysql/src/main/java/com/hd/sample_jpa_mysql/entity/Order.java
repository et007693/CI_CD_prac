package com.hd.sample_jpa_mysql.entity;

import com.hd.sample_jpa_mysql.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regDate;

    @OneToMany(mappedBy = "order") // 주가되는 테이블이 반대임(order) -> 데이터를 생성하는 쪽
    private List<OrderItem> orderItemList = new ArrayList<>();
}
