package com.example.boot_ex_board_from_servlet.serviceTests;

import com.example.boot_ex_board_from_servlet.dto.BoardDTO;
import com.example.boot_ex_board_from_servlet.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class ServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void addTest() {
        BoardDTO boardDTO = BoardDTO.builder()
                .name("하하하")
                .subject("호호호")
                .content("히히히")
                .ip("1.1.1.1")
                .build();
        boardService.add(boardDTO);
    }

    @Test
    public void getAllTest() {
        List<BoardDTO> boardDTOList = boardService.getAll();
        log.info(boardDTOList);
    }

    @Test
    public void getOneTest() {
        BoardDTO boardDTO = boardService.getOne(3L);
        log.info(boardDTO);
    }
    @Test
    public void ModifyTest() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2)
                .subject("제목변경2")
                .content("내용변경2")
                .build();
        boardService.modify(boardDTO);
    }
}
