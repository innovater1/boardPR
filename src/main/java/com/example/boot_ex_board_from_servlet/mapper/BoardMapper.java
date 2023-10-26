package com.example.boot_ex_board_from_servlet.mapper;

import com.example.boot_ex_board_from_servlet.domain.BoardVO;
import com.example.boot_ex_board_from_servlet.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    String getTime(); // 시간 나타내기
    void insert(BoardVO boardVO); // DB 삽입
    List<BoardVO> selectAll(); // 전체 확인
    BoardVO selectOne(Long bno); // 한 개 확인
    void delete(Long bno); // 삭제
    void update(BoardVO boardVO); //수정
    List<BoardVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
}
