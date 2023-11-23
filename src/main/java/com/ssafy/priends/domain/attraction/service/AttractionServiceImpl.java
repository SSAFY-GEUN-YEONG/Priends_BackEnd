package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.attraction.dto.AreaHomeDto;
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

	@Override
	public String getAreaHomeImg(String city, boolean isOnlySi) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.getAreaHomeImg(city, isOnlySi);
	}

	@Override
	public List<AttractionDto> getAttractionListAreaHome(String city, String category, boolean isOnlySi)
			throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.getAttractionListAreaHome(city, category, isOnlySi);
	}

	@Override
	public List<AttractionDto> getAttractionListAreaCategory(String city, String category, boolean isOnlySi, int order,
			int limitcount) throws Exception {
		return attractionMapper.getAttractionListAreaCategory(city, category, isOnlySi, order, limitcount);
	}

	@Override
	public void updateHit(int attractionId) throws Exception {
		attractionMapper.updateHit(attractionId);

	}

	@Override
	public String getAreaName(int sido, int gugun) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.getAreaName(sido, gugun);
	}

	@Override
	public List<AttractionDto> topGetAttractionList() {
		return attractionMapper.topGetAttractionList();
	}

	@Override
	public List<AttractionDto> recommendGetAttractionList() {
		return attractionMapper.recommendGetAttractionList();
	}

	@Override
	public List<AttractionDto> nearGetAttractionList(int attractionId) {
		return attractionMapper.nearGetAttractionList(attractionId);
	}
}
