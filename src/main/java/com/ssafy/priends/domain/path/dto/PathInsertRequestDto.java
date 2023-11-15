package com.ssafy.priends.domain.path.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PathInsertRequestDto {
    private String title;   // 여행 경로 제목(이름)
    private String content; // 여행 경로 내용
    private String startDate;    // 여행 시작 날짜 (ex. "YYYY-MM-DD")
    private String endDate;  // 여행 종료 날짜
    private Long memberId;  // 회원 id
}
