package com.ssafy.priends.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor	// 필드 전부 생성자
@NoArgsConstructor	// 기본 생성자
public class AttractionDto {
	
	private int content_id;
	private int content_type_id;
	private String title;		 
	private String addr1;		 
	private String addr2;	 
	private double latitude;		// 위도 
	private double longitude;		// 경도
	private String first_image;		
	private int readcount;
	
	private int sido_code;		//시도 코드
	private int gugun_code;		//구군 코드
	private String overview; 	// attraction_description
	
}
