package com.example.boot_ex_board_from_servlet.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVO {
    private Integer bno;
    private String name;
    private String subject;
    private String content;
    private Integer hit;
    private String ip;
    private LocalDateTime addDate;
}
