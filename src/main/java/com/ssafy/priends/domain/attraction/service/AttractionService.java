package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;

public interface AttractionService {
	
	List<AreacodeDto> getAreacode(int sido) throws Exception;
	
	
	
//	List<Gugun> searchGugun(int areaCode) throws Exception;
//	List<AttractionDto> searchAttraction(int sido, int gugun, int content_type_id, String keyword) throws Exception;
}
