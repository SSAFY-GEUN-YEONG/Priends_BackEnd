package com.ssafy.priends.domain.path.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PathDetailWithAttractionDto {
    private Long id;    // 여행 경로 상세 id
    private int orders; // 일자 별 순서 (1일차에서 1, 2, 3, ...)
    private int day;    // 여행 일자 (1일차, 2일차)
    private Long contentId; // 여행지(attraction) id
    private Long pathId;    // 여행 경로 id
    // 관광지 부분 (attraction_info)
    private String title;   // 관광지 명소 제목
    private String address; // 관광지 주소
    private String zipcode; // 관광지 우편번호
    private String image1;  // 관광지 이미지 1
    private String image2;  // 관광지 이미지 2
    private String sidoCode;    // 관광지 시도 코드
    private String gugunCode;   // 관광지 구군 코드
    private String latitude;    // 관광지 위도
    private String longitude;    // 관광지 경도
}
