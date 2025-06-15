package com.hd.sample_jpa_mysql.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 매개변수가 전부 있는 생성자
// @Data // 모든 어노테이션을 한번에 적용
@ToString // 자동 오버라이딩
public class LoginReqDto {
    private String email;
    private String pwd;
}
