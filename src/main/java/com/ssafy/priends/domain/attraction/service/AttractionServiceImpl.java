package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;
import com.ssafy.priends.domain.attraction.mapper.AttractionMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttractionServiceImpl implements AttractionService{
	
	private AttractionMapper attractionMapper;

 
	@Override
	public List<AttractionDto> searchAttraction(int area, int typeid, String keyword) throws Exception {
		return attractionMapper.searchAttraction(area, typeid, keyword);
	}
 
}
