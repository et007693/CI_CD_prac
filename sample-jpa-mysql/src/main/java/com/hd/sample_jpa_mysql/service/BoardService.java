package com.hd.sample_jpa_mysql.service;

import com.hd.sample_jpa_mysql.dto.BoardResDto;
import com.hd.sample_jpa_mysql.dto.BoardWriteDto;
import com.hd.sample_jpa_mysql.entity.Board;
import com.hd.sample_jpa_mysql.entity.Member;
import com.hd.sample_jpa_mysql.repository.BoardRepository;
import com.hd.sample_jpa_mysql.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor

public class BoardService {
    private final BoardRepository boardRepository; // 의존성 주입
    private final MemberRepository memberRepository; // join

    // 게시글 등록
    public boolean addBoard(BoardWriteDto boardWriteDto) {
        try {
            Board board = convertDtoToEntity(boardWriteDto);
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            log.info("입력된 이메일: {}", boardWriteDto.getEmail());
            log.error("게시글 등록에 실패했습니다: {}", e.getMessage());
            return false;
        }
    }

    // 게시글 전체 조회 : 반환값 List<Board>
    public List<BoardResDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardResDto> boardResDto = new ArrayList<>();
        for (Board board : boards) {
            boardResDto.add(convertEntityToDto(board));
        }
        return boardResDto;
    }

    // 게시글 상세 조회 : 반환값 Board
    public BoardResDto getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId).
                orElseThrow(() -> new RuntimeException("해당 게시물이 없습니다.")
        );
        return convertEntityToDto(board);
    }

    // 게시글 수정
    public boolean updateBoard(Long boardId, BoardWriteDto boardWriteDto) {
        try {
            Board board = boardRepository.findById(boardId).
                    orElseThrow(() -> new RuntimeException("해당 게시물이 없습니다."));
            Member member= memberRepository.findByEmail(boardWriteDto.getEmail()).
                    orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다"));
            board.setTitle(boardWriteDto.getTitle());
            board.setContent(boardWriteDto.getContent());
            board.setImage(boardWriteDto.getImage());
            board.setMember(member);
            boardRepository.save(board);
            return true;
        } catch (RuntimeException e) {
            log.error("게시물 수정 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 게시글 삭제
    public boolean deleteBoard(Long boardId) {
        try {
            Board board = boardRepository.findById(boardId).
                    orElseThrow(() -> new RuntimeException("해당 게시물이 없습니다.")
            );
            boardRepository.delete(board);
            return true;
        } catch (RuntimeException e) {
            log.error("게시물 삭제 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 게시물 검색 : List<Board>
    public List<BoardResDto> findBoard(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardResDto> boardResDto = new ArrayList<>(boards.size());
        for (Board board : boards) {
            boardResDto.add(convertEntityToDto(board));
        }
        return boardResDto;
    }

    // 게시글 페이징 처리
    public PageResDto<BoardResDto> getBoardPageList(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Board> boardPage = boardRepository.findAll(pageRequest);
        Page<BoardResDto> boardResDtoPage = boardPage.map(this::convertEntityToDto);
        return new PageResDto<>(boardResDtoPage);
    }

    // Entity -> Dto
    private BoardResDto convertEntityToDto(Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setBoardId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setEmail(board.getMember().getEmail());
        boardResDto.setImg(board.getImage());
        boardResDto.setCreateTime(board.getCreateTime());
        return boardResDto;
    }

    // Dto -> Entity
    private Board convertDtoToEntity(BoardWriteDto boardWriteDto) {
        Member member = memberRepository.findByEmail(boardWriteDto.getEmail()).orElseThrow(
                () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
        );
        Board board = new Board();
        board.setTitle(boardWriteDto.getTitle());
        board.setContent(boardWriteDto.getContent());
        board.setMember(member);
        board.setImage(boardWriteDto.getImage());
        return board;
    }

    @Getter
    public class PageResDto<T> {
        private final List<T> content;
        private final int pageNumber;
        private final int pageSize;
        private final long totalElements;
        private final int totalPages;
        private final boolean last;

        public PageResDto(Page<T> page) {
            this.content = page.getContent();
            this.pageNumber = page.getNumber();
            this.pageSize = page.getSize();
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
            this.last = page.isLast();
        }
    }
}
