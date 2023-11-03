package com.ssafy.priends.domain.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.attraction.dto.AttractionDto;

@Mapper
public interface AttractionMapper {

	List<AttractionDto> searchAttraction(int area, int typeid, String keyword) throws SQLException;

}
