package com.ssafy.priends.domain.attraction.service;

import java.util.List;

import com.ssafy.priends.domain.attraction.dto.AreaHomeDto;
import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;

public interface AttractionService {

	List<AttractionDto> searchAttractions(int area, int typeid, String keyword) throws Exception;

	AttractionDto getAttraction(int attractionId) throws Exception;

	List<AreacodeDto> getAllArea(String word) throws Exception;

	String getAreaHomeImg(String city,boolean isOnlySi) throws Exception;


	List<AttractionDto> getAttractionListAreaHome(String city, String category, boolean isOnlySi)throws Exception;
	List<AttractionDto> getAttractionListAreaCategory(String city, String category, boolean isOnlySi, int order, int limitcount) throws Exception;

	void updateHit(int attractionId) throws Exception;

	String getAreaName(int sido, int gugun) throws Exception;


	List<AttractionDto> topGetAttractionList();

	List<AttractionDto> recommendGetAttractionList();

	List<AttractionDto> nearGetAttractionList(int attractionId);
} 