package com.ssafy.priends.domain.path.controller;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.member.dto.MemberLoginActiveDto;
import com.ssafy.priends.domain.path.dto.*;
import com.ssafy.priends.domain.path.service.PathService;
import com.ssafy.priends.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/path")
@Slf4j
@RequiredArgsConstructor
public class PathController {

	private final PathService pathService;

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Long>> createPath(@RequestBody PathInsertRequestDto pathInsertRequestDto,
			@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {

		System.out.println("create " + pathInsertRequestDto);
		Long createdPathId = pathService.createPath(pathInsertRequestDto, memberLoginActiveDto.getId());
		return ResponseEntity.ok().body(Message.success(createdPathId));

	}

	@GetMapping("/get/list")
	public ResponseEntity<Message<List<PathGetDto>>> getPathList(@RequestParam Long pathId,@RequestParam String city, @RequestParam int order,
			@RequestParam int limitCount) {
		System.out.println("order, "+ order);
		List<PathGetDto> pathList = pathService.getPathList(pathId, city, order, limitCount);
		System.out.println(pathList);
		return ResponseEntity.ok().body(Message.success(pathList));
	}

	@GetMapping("/get/mylist")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<List<PathGetDto>>> getMyPathList(
			@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		List<PathGetDto> pathList = pathService.getMyPathList(memberLoginActiveDto.getId());
		return ResponseEntity.ok().body(Message.success(pathList));
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> updatePath(@RequestBody PathUpdateRequestDto pathUpdateRequestDto) {
		pathService.updatePath(pathUpdateRequestDto);
		return ResponseEntity.ok().body(Message.success());
	}

	@PutMapping("/delete/{pathId}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> deletePath(@PathVariable("pathId") Long pathId) {
		pathService.deletePath(pathId);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/detail/get/list/{pathId}")
//	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<List<PathDetailWithAttractionDto>>> getPathDetailList(
			@PathVariable("pathId") Long pathId) {
		System.out.println("detail get list with attarction");
		 
		pathService.updateHit(pathId);
		List<PathDetailWithAttractionDto> pathDetails = pathService.getPathDetailsWithAttraction(pathId);
		System.out.println(pathDetails.get(0));
		return ResponseEntity.ok().body(Message.success(pathDetails));
	}

	@PostMapping("/detail/create/{pathId}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> createAndUpdatePathDetails(@PathVariable("pathId") Long pathId,
			@RequestBody List<PathDetailInsertRequestDto> pathDetailInsertRequestDtoList) {
		System.out.println("detail create");
		System.out.println("id " + pathId);
		System.out.println("dto " + pathDetailInsertRequestDtoList);
		pathService.createAndUpdatePathDetails(pathId, pathDetailInsertRequestDtoList);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/make/sido")
	public ResponseEntity<Message<List<AreacodeDto>>> sido() throws Exception {
		System.out.println("sido - 호출");
		List<AreacodeDto> sidoList = pathService.getSido();
		return ResponseEntity.ok().body(Message.success(sidoList));
	}

	@GetMapping("/make/gugun")
	public ResponseEntity<Message<List<AreacodeDto>>> gugun(@RequestParam("sido") int sido) throws Exception {
		System.out.println("gugun - 호출");
		List<AreacodeDto> gugunList = pathService.getGugun(sido);
		return ResponseEntity.ok().body(Message.success(gugunList));
	}

}
