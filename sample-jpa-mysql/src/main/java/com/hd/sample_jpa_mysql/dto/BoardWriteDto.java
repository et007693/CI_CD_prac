package com.hd.sample_jpa_mysql.dto;

import lombok.Data;

// 게시글 쓰기
@Data
public class BoardWriteDto {
    private String email;
    private String title;
    private String content;
    private String image;
}
