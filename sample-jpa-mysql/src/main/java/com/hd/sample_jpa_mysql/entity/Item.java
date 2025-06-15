package com.hd.sample_jpa_mysql.entity;

import com.hd.sample_jpa_mysql.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString // toString() 오버라이드 자동으로 해줌
@Entity
@Table(name="item")

public class Item {
    @Id // 기본키 필드 지정, 유일한 값, Not null, 필수요소
    @Column(name="item_id") // DB로 생성될 컬럼,, 이름을 지정
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략, JPA가 DB에 맞게 생성전략을 생성
    private long id;

    @Column(nullable = false, length = 50)
    private String itemNum;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}
