package com.example.boot_ex_board_from_servlet.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Integer bno;
    private String name;
    private String subject;
    private String content;
    private Integer hit;
    private String ip;
    private LocalDateTime addDate;
}
