package com.hd.sample_jpa_mysql.controller;

import com.hd.sample_jpa_mysql.dto.BoardResDto;
import com.hd.sample_jpa_mysql.dto.BoardWriteDto;
import com.hd.sample_jpa_mysql.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    // 게시글 등록 : 입력(BoardWriteDto), 반환(boolean)
    @PostMapping("/add")
    public ResponseEntity<Boolean> addBoard(@RequestBody BoardWriteDto boardWriteDto) {
        return ResponseEntity.ok(boardService.addBoard(boardWriteDto));
    }

    // 게시글 수정 : 입력(id, BoardWriteDto), 반환(boolean)
    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> updateBoard(@PathVariable Long id, @RequestBody BoardWriteDto boardWriteDto) {
        return ResponseEntity.ok(boardService.updateBoard(id, boardWriteDto));
    }

    // 게시글 삭제 : 입력(id), 반환(boolean)
    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }

    // 게시글 검색 : 입력(keyword), 반환(List<BoardResDto>)
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<BoardResDto>> searchBoard(@PathVariable String keyword) {
        return ResponseEntity.ok(boardService.findBoard(keyword));
    }

    // 게시글 전체 조회 : 입력(), 반환(List<BoardResDto>)
    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> getBoards(BoardWriteDto boardWriteDto) {
        return ResponseEntity.ok(boardService.getBoards());
    }

    // 게시글 상세 조회 : 입력(id), 반환(BoardResDto)
    @GetMapping("/{id}")
    public ResponseEntity<BoardResDto> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardDetail(id));
    }

    // 게시글 페이징 : 입력(page_num, page_size), 반환(PageResDto)
    @GetMapping("/page/{page_num}/{page_size}")
    public ResponseEntity<BoardService.PageResDto<BoardResDto>> pageBoard(
            @PathVariable("page_num") Integer pageNum,
            @PathVariable("page_size") Integer pageSize) {
        return ResponseEntity.ok(boardService.getBoardPageList(pageNum, pageSize));
    }
}
