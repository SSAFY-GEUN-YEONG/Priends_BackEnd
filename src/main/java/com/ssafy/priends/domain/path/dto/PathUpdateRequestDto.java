package com.ssafy.priends.domain.path.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PathUpdateRequestDto {
    private Long id;    // 여행 경로 id
    private String title;   // 여행 경로 제목 (이름)
    private String content; // 여행 경로 내용
    private Boolean isEnd;  // 여행 완료 여부
    private String startDate;   // 여행 시작 날짜
    private String endDate; // 여행 종료 날짜
}
