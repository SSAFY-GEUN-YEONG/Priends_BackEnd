package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;
import com.ssafy.priends.domain.attraction.mapper.AttractionMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

	private final AttractionMapper attractionMapper;

	@Override
	public List<AttractionDto> searchAttractions(int area, int typeid, String keyword) throws Exception {
		return attractionMapper.searchAttractions(area, typeid, keyword);
	}

	@Override
	public AttractionDto getAttraction(int attractionId) throws Exception {
		return attractionMapper.getAttraction(attractionId);
	}

	@Override
	public List<AreacodeDto> getAllArea(String word) throws Exception {
		System.out.println(word);
		return attractionMapper.getAllArea(word);
	}

}
