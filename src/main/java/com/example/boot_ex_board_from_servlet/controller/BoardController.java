package com.example.boot_ex_board_from_servlet.controller;

import com.example.boot_ex_board_from_servlet.dto.BoardDTO;
import com.example.boot_ex_board_from_servlet.dto.PageRequestDTO;
import com.example.boot_ex_board_from_servlet.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 글 추가 get 방식 매핑
    @GetMapping("/add")
    public void add() {
        log.info("/board/add...");
    }

    // 글 추가 post 방식 매핑
    @PostMapping("/add")
    public String addPost(BoardDTO boardDTO, HttpServletRequest request) {
        log.info("/board/add post...");
        log.info(boardDTO);
        boardDTO.setIp(request.getRemoteAddr());
        boardService.add(boardDTO);
        return "redirect:/board/list";
    }

    // 리스트 get 방식 매핑
    @GetMapping("/list")
    public void list(Model model) {
        log.info("/board/list...");
        List<BoardDTO> boardDTOList = boardService.getAll();
        model.addAttribute("boardDTOList", boardDTOList);
    }

    // 읽기, 수정 페이지 매핑 (get)
    @GetMapping({"/view", "/modify"})
    public void read(Long bno, BoardDTO boardDTO, Model model) {
        boardDTO = boardService.getOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }

    // 수정 페이지 매핑 (post)
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("board modify post......" + boardDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors......");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("bno", boardDTO.getBno());

            return "redirect:/board/modify?"+link;
        }
        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/view";
    }

    // 삭제 페이지 매핑 (post)
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("remove post..." + bno);
        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";
    }


}
