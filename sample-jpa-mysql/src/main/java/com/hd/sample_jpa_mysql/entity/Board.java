package com.hd.sample_jpa_mysql.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // board_id

    @Column(length = 256, nullable = false)
    private String title; // 게시글 제목

    @Lob // 대용량 텍스트
    private String content; // 게시글 내용
    private String image; // 게시글에 첨부될 이미지
    private LocalDateTime createTime;
    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now(); // DB에 쓰여지기 직전에 시간이 포함됨
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원정보 객체

}
