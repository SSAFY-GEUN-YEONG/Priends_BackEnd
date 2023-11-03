package com.ssafy.priends.domain.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;

@Mapper
public interface AttractionMapper {

	List<AreacodeDto> getAreacode(int sido) throws SQLException;
//	List<Gugun> searchGugun(int areaCode) throws SQLException;
//	List<AttractionDto> searchAttraction(int sido, int gugun, int content_type_id, String keyword) throws SQLException;

}
