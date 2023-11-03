package com.ssafy.priends.domain.attraction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;
import com.ssafy.priends.domain.attraction.service.AttractionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/attraction")
@AllArgsConstructor
public class AttractionController {
	private AttractionService attractionService;

	
	/**
	 * 
	 * @param area : sido 테이블의 sido_code 그대로 사용. 필수 입력
	 * @param typeid : attraction_info테이블 content_type_id 
	 * @param keyword : 검색어 입력. 필수 입력x
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/search")
	public ResponseEntity<?> searchAttraction(@RequestParam int area, @RequestParam int typeid, @RequestParam(required = false) String keyword)
			throws Exception {

		List<AttractionDto> attractions = attractionService.searchAttraction(area, typeid, keyword);
		return ResponseEntity.ok().body(attractions);
	}

}
