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
	public List<AreacodeDto> getAreacode(int sido) throws Exception {
		return attractionMapper.getAreacode(sido);
	}

//	@Override
//	public List<AttractionDto> searchAttraction(int sido, int gugun, int content_type_id, String keyword)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
