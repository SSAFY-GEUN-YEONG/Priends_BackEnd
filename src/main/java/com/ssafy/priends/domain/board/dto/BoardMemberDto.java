package com.ssafy.priends.domain.board.dto;

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
public class BoardMemberDto {
	
	private Long id;
	private String title;
	private String content;
	private int hit;
	private int like;
	private String createdAt;
	private String updatedAt;
	private boolean isdeleted;
	private String category;
	
	private Long member_id;
	private String member_email;
	private String member_nickname;

	
}
