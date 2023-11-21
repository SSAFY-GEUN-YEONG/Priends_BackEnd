package com.ssafy.priends.domain.path.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PathDetailInsertRequestDto {
    private Long id;
    private int orders; // 일자 별 순서 (1일차에서 1, 2, 3, ...)
    private int day;    // 여행 일자 (1일차, 2일차)
    private Long contentId; // 여행지(attraction) id
}
