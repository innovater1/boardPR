package com.example.boot_ex_board_from_servlet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Getter
@ToString
@Log4j2
public class PageResponseDTO<E> {
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;
        //끝페이지 번호 10, 20, 30
        this.end = (int) (Math.ceil(this.page/10.0)) * 10;
        //시작페이지 1, 11, 21
        this.start = this.end - 9;

        int last = (int)(Math.ceil((total/(double)size)));

        this.end = end > last ? last : end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;

        //ex
        //size = 10, page = 23, total = 821
        //start = 21, end = 30, last = 83, prev=true, next=true;
    }


    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

}
