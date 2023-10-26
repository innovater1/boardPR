package com.example.boot_ex_board_from_servlet.mybatis_mapper_tests;

import com.example.boot_ex_board_from_servlet.domain.BoardVO;
import com.example.boot_ex_board_from_servlet.mapper.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class MyBatisMapperTests {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() { // 글 등록 테스트 코드
        BoardVO boardVO = BoardVO.builder()
                .name("홍길동")
                .subject("안녕하세요")
                .content("저는 홍길동 입니다.")
                .hit(1)
                .ip("0.0.0.1")
                .build();
        boardMapper.insert(boardVO);
    }

    @Test
    public void testGetTime() { // 시간 Get 테스트 코드
        log.info(boardMapper.getTime());
    }

    @Test
    public void testSelectAll(){ // 전체 보기 테스트 코드
        List<BoardVO> boardVOList = boardMapper.selectAll();
        boardVOList.forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testUpdate(){
        BoardVO boardVO = BoardVO.builder()
                .bno(1)
                .subject("제목변경")
                .content("내용변경")
                .build();
        boardMapper.update(boardVO);
        log.info(boardMapper.selectOne(1L));
    }

    @Test
    public void testDelete(){
        Long tno = 4L;
        log.info(boardMapper.selectOne(tno));
        boardMapper.delete(tno);
        log.info(boardMapper.selectOne(tno));
    }
}
