package com.example.boot_ex_board_from_servlet.service;

import com.example.boot_ex_board_from_servlet.dto.BoardDTO;
import com.example.boot_ex_board_from_servlet.dto.PageRequestDTO;
import com.example.boot_ex_board_from_servlet.dto.PageResponseDTO;

import java.util.List;

public interface BoardService {
    void add(BoardDTO boardDTO);
    List<BoardDTO> getAll();
    BoardDTO getOne(Long bno);
    void remove(Long bno);
    void modify(BoardDTO boardDTO);
}
