package com.hd.sample_jpa_mysql.repository;

import com.hd.sample_jpa_mysql.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // email 존재 여부 확인 : 존재하면 true
    boolean existsByEmail(String email); // query 메소드 작명 규칙

    // 이메일로 회원 정보 가져오기, Optional은 null값 처리
    Optional<Member> findByEmail(String email);
    
    // 로그인 성공 여부 확인을 위해 email과 pwd가 존재하는지 확인
    Optional<Member> findByEmailAndPwd(String email, String pwd);

}
