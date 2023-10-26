package com.example.boot_ex_board_from_servlet.service;

import com.example.boot_ex_board_from_servlet.domain.BoardVO;
import com.example.boot_ex_board_from_servlet.dto.BoardDTO;
import com.example.boot_ex_board_from_servlet.dto.PageRequestDTO;
import com.example.boot_ex_board_from_servlet.dto.PageResponseDTO;
import com.example.boot_ex_board_from_servlet.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;


    @Override
    public void add(BoardDTO boardDTO) {
        log.info(boardDTO);
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class); // 등록할 곳 정의 + 서치
        log.info(boardVO);
        boardMapper.insert(boardVO); // 저장할 bno 정의

    }

    @Override
    public List<BoardDTO> getAll() {
        List<BoardVO> boardVOList = boardMapper.selectAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardVOList.forEach(boardVO -> boardDTOList.add(modelMapper.map(boardVO, BoardDTO.class)));
        return boardDTOList;
    }

    @Override
    public BoardDTO getOne(Long bno) {
        BoardVO boardVO = boardMapper.selectOne(bno);
        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);

        return boardDTO;
    }

    @Override
    public void remove(Long bno) {
        boardMapper.delete(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.update(boardVO);
    }

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
        List<BoardVO> voList = boardMapper.selectList(pageRequestDTO);
//        List<TodoDTO> todoList = todoMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
        List<BoardDTO> dtoList = new ArrayList<>();
        for(BoardVO boardVO : voList) {
            dtoList.add(modelMapper.map(boardVO, BoardDTO.class));
        }

        int total = boardMapper.getCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

}
