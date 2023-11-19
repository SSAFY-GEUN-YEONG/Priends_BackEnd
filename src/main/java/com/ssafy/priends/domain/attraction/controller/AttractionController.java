package com.ssafy.priends.domain.attraction.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.attraction.dto.AreaHomeDto;
import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.attraction.dto.AttractionDto;
import com.ssafy.priends.domain.attraction.service.AttractionService;
import com.ssafy.priends.global.common.dto.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attraction")
@Slf4j
@RequiredArgsConstructor
public class AttractionController {

	private final AttractionService attractionService;
	private String[] maincity = { "서울", "대전", "대구", "부산", "울산", "인천", "광주", "세종" };

	@GetMapping("/main")
	public ResponseEntity<Message<List<AreacodeDto>>> getAllArea(@RequestParam(required = false) String word)
			throws Exception {
		System.out.println("word" + word);
		List<AreacodeDto> areas = attractionService.getAllArea(word);
		return ResponseEntity.ok().body(Message.success(areas));
	}

	@GetMapping("/area/{city}/home")
	public ResponseEntity<Message<AreaHomeDto>> getAreaHome(@RequestParam String city, @RequestParam String category)
			throws Exception {
		boolean isOnlySi = Arrays.asList(maincity).contains(city);
		System.out.println(city + " " + isOnlySi);

		// 커버 이미지
		AreaHomeDto areaHome = new AreaHomeDto();
		areaHome.setImg(attractionService.getAreaHomeImg(city, isOnlySi));

		// 관광명소가져오기
		areaHome.setNatureAttractions(attractionService.getAttractionListAreaHome(city,"nature", isOnlySi ));
		areaHome.setRestaurantAttractions(attractionService.getAttractionListAreaHome(city,"restaurant", isOnlySi ));
		areaHome.setCultureAttractions(attractionService.getAttractionListAreaHome(city,"culture", isOnlySi ));
		System.out.println(areaHome);
		return ResponseEntity.ok().body(Message.success(areaHome));

	}
	
	@GetMapping("/area/{city}/{category}")
	public ResponseEntity<Message<List<AttractionDto>>> getAreaList(@RequestParam String city, @RequestParam String category)
			throws Exception {
		System.out.println(category);
		boolean isOnlySi = Arrays.asList(maincity).contains(city); 

		List<AttractionDto> attractionList= attractionService.getAttractionListAreaCategory(city,category, isOnlySi, 1 );
		
		
		return ResponseEntity.ok().body(Message.success(attractionList));

	}

	/**
	 * 관광지 지역, 유형, 키워드 별 검색
	 * 
	 * @param area    : sido 테이블의 sido_code 그대로 사용. 필수 입력
	 * @param typeid  : attraction_info테이블 content_type_id {0:전체, 1:호텔/32, 2:음식점/39,
	 *                3:마켓/38, 4:문화생활/14.15, 5:액티비티/28, 6:자연/12.25 }
	 * @param keyword : 검색어 입력. 필수 입력x
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/search")
	public ResponseEntity<Message<List<AttractionDto>>> searchAttractions(@RequestParam int area,
			@RequestParam int typeid, @RequestParam(required = false) String keyword) throws Exception {
		List<AttractionDto> attractions = attractionService.searchAttractions(area, typeid, keyword);
		return ResponseEntity.ok().body(Message.success(attractions));
	}

	/**
	 * 관광지 상세 조회
	 */
	@GetMapping("/{attractionId}/view")
	public ResponseEntity<Message<AttractionDto>> getAttraction(@PathVariable("attractionId") int attractionId)
			throws Exception {
		AttractionDto attraction = attractionService.getAttraction(attractionId);
		return ResponseEntity.ok().body(Message.success(attraction));
	}

}
