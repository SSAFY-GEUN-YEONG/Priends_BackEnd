package com.ssafy.priends.domain.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.attraction.dto.AreaHomeDto;
import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;

@Mapper
public interface AttractionMapper {

	List<AttractionDto> searchAttractions(int area, int typeid, String keyword) throws SQLException;

	AttractionDto getAttraction(int attractionId) throws SQLException;

	List<AreacodeDto> getAllArea(String word) throws SQLException;

	String getAreaHomeImg(String city, boolean isOnlySi) throws SQLException;

	List<AttractionDto> getAttractionListAreaHome(String city, String category, boolean isOnlySi);

	List<AttractionDto> getAttractionListAreaCategory(String city, String category, boolean isOnlySi, int order,
			int limitcount) throws SQLException;

	void updateHit(int attractionId) throws SQLException;

	String getAreaName(int sido, int gugun) throws SQLException;

	List<AttractionDto> topGetAttractionList();

	List<AttractionDto> recommendGetAttractionList();

	List<AttractionDto> nearGetAttractionList(int attractionId);
}
