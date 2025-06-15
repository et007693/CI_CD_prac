package com.hd.sample_jpa_mysql.controller;

import com.hd.sample_jpa_mysql.dto.LoginReqDto;
import com.hd.sample_jpa_mysql.dto.SignUpReqDto;
import com.hd.sample_jpa_mysql.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor // 생성자를 통한 의존성 주입을 받이 위해 생성자를 자동 생성
@CrossOrigin(origins = {
        "https://localhost:3000",
        "https://localhost:5173",
})
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 회원 가입 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean isExist = authService.isMember(email);
        return ResponseEntity.ok(!isExist);
    }

    // 회원 가입
    @PostMapping("/signup") // Body에 정보를 싣는 방식, 정보가 보여지지 않음
    public ResponseEntity<Boolean> signup(@RequestBody SignUpReqDto memberReqDto) {
        boolean isSuccess = authService.signup(memberReqDto);
        return ResponseEntity.ok(isSuccess);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginReqDto loginReqDto) {
        boolean isSuccess = authService.login(loginReqDto.getEmail(), loginReqDto.getPwd());
        return ResponseEntity.ok(isSuccess);
    }


//    private String email;
//    private String pwd;
//    private String name;

//    @PostMapping("/signup")
//    public ResponseEntity<MemberResDto> signup(@RequestBody MemberRegDto memberRegDto) {
//        log.info("member : {}", memberRegDto);
//        MemberResDto memberResDto = new MemberResDto();
//        memberResDto.setEmail(memberRegDto.getEmail());
//        memberResDto.setName(memberRegDto.getName());
//        memberResDto.setPwd(memberRegDto.getPwd());
//        memberResDto.setImage("/test/test.img");
//        memberResDto.setRegDate(LocalDateTime.now());
//        return ResponseEntity.ok(memberResDto);
//    }

    // 로그인
    // post 방식 : email, pwd를 request Body 형식으로 수신
    // 응답은 boolean 값으로 전달
//    @PostMapping("/login")
//    public ResponseEntity<Boolean> login(@RequestBody LoginReqDto LoginReqDto) {
//        log.debug("login : {}", LoginReqDto);
//        return ResponseEntity.ok(true);
//    }

    // 회원 조회
    // Get 방식 : email이 있으면 해당 회원 조회, 없으면 전체 회원 조회
    // 단, 회원 정보 리스트는 서비스 로직과 DB가 없으므로 for문으로 자체 생성
//    @GetMapping("/members")
//    public List<Map<String, Object>> findMembers() {
//        log.info("member : {}", MemberResDto);
//    }
}
