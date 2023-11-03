package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import com.ssafy.priends.domain.attraction.dto.AttractionDto;

public interface AttractionService {
	
	List<AttractionDto> searchAttraction(int area, int typeid, String keyword) throws Exception;
}
