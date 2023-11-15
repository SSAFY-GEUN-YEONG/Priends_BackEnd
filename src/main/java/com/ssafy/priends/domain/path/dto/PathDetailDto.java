package com.ssafy.priends.domain.path.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PathDetailDto {
    private Long id;    // 여행 경로 상세 id
    private int orders; // 일자 별 순서 (1일차에서 1, 2, 3, ...)
    private int day;    // 여행 일자 (1일차, 2일차)
    private Long contentId; // 여행지(attraction) id
    private Long pathId;    // 여행 경로 id
}
