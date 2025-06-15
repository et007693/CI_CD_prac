package com.hd.sample_jpa_mysql.controller;

import com.hd.sample_jpa_mysql.dto.MemberReqDto;
import com.hd.sample_jpa_mysql.dto.MemberResDto;
import com.hd.sample_jpa_mysql.repository.MemberRepository;
import com.hd.sample_jpa_mysql.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor // 생성자를 통한 의존성 주입을 받이 위해 생성자를 자동 생성
@CrossOrigin(origins = {
        "https://localhost:3000",
        "https://localhost:5173",
})
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberResDto>> getMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 회원 상세 조회
    @GetMapping("/detail/{email}")
    public ResponseEntity<MemberResDto> getMember(@PathVariable String email) {
        return ResponseEntity.ok(memberService.findByEmail(email));
    }

    // 회원 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<Boolean> modifyMember(@RequestBody MemberReqDto memberReqDto) {
        boolean isSuccess = memberService.modifyMember(memberReqDto);
        return ResponseEntity.ok(isSuccess);
    }

    // 회원 삭제
    @PostMapping("/delete/{email}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable String email) {
        boolean isSuccess = memberService.deleteMember(email);
        return ResponseEntity.ok(isSuccess);
    }
}

