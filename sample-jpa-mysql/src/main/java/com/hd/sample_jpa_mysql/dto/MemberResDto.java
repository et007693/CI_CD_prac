package com.hd.sample_jpa_mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResDto {
    private String email;
    private String name;
    private String pwd;
    private String image;
    private LocalDateTime regDate;
}
