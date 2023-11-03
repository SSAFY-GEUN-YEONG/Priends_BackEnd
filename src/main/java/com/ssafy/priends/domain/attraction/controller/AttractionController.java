package com.ssafy.priends.domain.attraction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.service.AttractionService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/attraction")
@AllArgsConstructor
public class AttractionController {
	private AttractionService attractionService;
	
	@GetMapping("/area")
	public ResponseEntity<?> getAreacode(@RequestParam(required = false) Integer  sido) throws Exception {
		if(sido == null) {
			sido = (int) 0;
		}
		List<AreacodeDto> list =  attractionService.getAreacode(sido);						
		return ResponseEntity.ok().body(list);
	}
	
}
