package com.ssafy.priends.domain.board.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardListDto {
	private List<BoardMemberDto> articles;
	private int currentPage;	//현재 페이지 번호
	private int totalPageCount;	//전체 페이지 수
}
