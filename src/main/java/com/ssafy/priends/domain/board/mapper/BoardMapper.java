package com.ssafy.priends.domain.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardMemberDto;

@Mapper
public interface BoardMapper {
	
	void writePost(BoardDto board) throws SQLException;

	BoardMemberDto getPost(long id) throws SQLException;

	List<BoardMemberDto> listPost(String category) throws SQLException;

	void updateHit(long id) throws SQLException;

	void modifyPost(BoardDto board) throws SQLException;

	void deletePost(long id) throws SQLException;
	
}
