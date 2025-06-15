package com.hd.sample_jpa_mysql.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResDto {
    private Long boardId; // 게시글 번호, 댓글 작성시 필요
    private String email; // 작성자, 연관관계 매핑을 이용해 값을 가져옴
    private String title;
    private String content;
    private String img;
    private LocalDateTime createTime;
}
