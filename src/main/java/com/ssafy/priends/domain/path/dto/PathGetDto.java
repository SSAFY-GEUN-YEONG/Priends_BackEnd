package com.ssafy.priends.domain.path.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PathGetDto {
    private Long id;    // 여행 경로 id
    private String title;   // 여행 경로 제목 (이름)
    private String content; // 여행 경로 내용
    private int hit;    // 조회수
    private Boolean isEnd;  // 여행 완료 여부
    private LocalDate startDate;    // 여행 시작 날짜 (ex. "YYYY-MM-DD")
    private LocalDate endDate;  // 여행 종료 날짜
}
