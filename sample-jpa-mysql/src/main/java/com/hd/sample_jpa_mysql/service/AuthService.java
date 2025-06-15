package com.hd.sample_jpa_mysql.service;

import com.hd.sample_jpa_mysql.dto.SignUpReqDto;
import com.hd.sample_jpa_mysql.entity.Member;
import com.hd.sample_jpa_mysql.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j // log 정보 출력하기 위한 annotation
@Service // spring container에 Bean 등록
@Transactional // 여러개의 물리적 작업 단위를 논리적 단위로 묶음
@RequiredArgsConstructor // 생성자를 자동 생성
public class AuthService {
    private final MemberRepository memberRepository; // 생성자를 통한 의존성 주입
//    AuthService(MemberRepository memberRepository) { // == @RequiredArgsConstructor
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입 여부 확인
    public boolean isMember(String email) {
        return memberRepository.existsByEmail(email);
    }
    // 회원 가입
    public boolean signup(SignUpReqDto memberReqDto) {
        try {
            Member member = convertDtoToEntity(memberReqDto);
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            log.error("회원 가입시 오류 발생 : {}", e.getMessage());
            return false;
        }
    }

    // 로그인
    public boolean login(String email, String pwd) {
        Optional<Member> member = memberRepository.findByEmailAndPwd(email, pwd);
        return member.isPresent(); // 객체 존재 여부 확인
    }

    // DTO -> Entity Mapping
    private Member convertDtoToEntity(SignUpReqDto memberReqDto) {
        Member member = new Member();
        member.setEmail(memberReqDto.getEmail());
        member.setPwd(memberReqDto.getPwd());
        member.setName(memberReqDto.getName());
        return member;
    }
}
