package com.ssafy.priends.domain.path.service;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.path.dto.*;
import com.ssafy.priends.domain.path.mapper.PathMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PathServiceImpl implements PathService {

	private final PathMapper pathMapper;

	@Override
	public Long createPath(PathInsertRequestDto pathInsertRequestDto, Long memberId) {
		log.info(pathInsertRequestDto.getStartDate());

		LocalDate startDate = LocalDate.parse(pathInsertRequestDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate endDate = LocalDate.parse(pathInsertRequestDto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE);

		PathDto path = PathDto.builder().title(pathInsertRequestDto.getTitle())
				.content(pathInsertRequestDto.getContent()).startDate(startDate).endDate(endDate).memberId(memberId)
				.build();

		pathMapper.createPath(path);
		return path.getId();
	}

	@Override
	public List<PathGetDto> getPathList(Long pathId, String city, int order, int limitCount) {
		return pathMapper.getPathList(pathId, city, order, limitCount);
	}

	@Override
	public List<PathGetDto> getMyPathList(Long memberId) {
		return pathMapper.getMyPathList(memberId);
	}

	@Override
	public void updatePath(PathUpdateRequestDto pathUpdateRequestDto) {
		LocalDate startDate = LocalDate.parse(pathUpdateRequestDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate endDate = LocalDate.parse(pathUpdateRequestDto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE);

		PathDto path = PathDto.builder().id(pathUpdateRequestDto.getId()).title(pathUpdateRequestDto.getTitle())
				.content(pathUpdateRequestDto.getContent()).isEnd(pathUpdateRequestDto.getIsEnd()).startDate(startDate)
				.endDate(endDate).build();

		pathMapper.updatePath(path);
	}

	@Override
	public void deletePath(Long pathId) {
		try {
			pathMapper.deletePath(pathId);
		} catch (Exception e) {
			throw new RuntimeException("해당 여행경로 정보가 없습니다.");
		}
	}

	// 여행 경로 상세 생성할 때 클라이언트쪽에서 수정 및 삭제할 시 다 처리되게끔
	@Override
	public void createAndUpdatePathDetails(Long pathId, List<PathDetailInsertRequestDto> newPathDetails) {
		// 새로운 상세 경로들이 없는 경우 (즉, 클라이언트단에서 상세 경로를 만들지 않은 경우 -> 비어있는 리스트)
		if (newPathDetails == null || newPathDetails.isEmpty()) {
			throw new RuntimeException("생성할 여행 경로 상세가 비어있습니다.");
		}

		// 기존 상세 경로를 가져옴
		List<PathDetailDto> existingDetails = pathMapper.getSimplePathDetails(pathId);

		// dto를 id 기반으로 맵에 저장
		Map<Long, PathDetailDto> existingDetailsMap = existingDetails.stream()
				.collect(Collectors.toMap(PathDetailDto::getId, Function.identity()));

		// 변경을 감지하고 적용한다
		for (PathDetailInsertRequestDto newDetail : newPathDetails) {
			if (newDetail.getId() == null) {
				// 새로운 상세 경로를 생성
				createNewPathDetail(pathId, newDetail);
			} else if (existingDetailsMap.containsKey(newDetail.getId())) {
				// 기존 상세 경로를 업데이트한다
				updateExistingPathDetail(newDetail);
				// 업데이트 된 항목은 맵에서 제거
				existingDetailsMap.remove(newDetail.getId());
			}
		}

		// 맵에 남아있는 항목은 더 이상 클라이언트에 의해 전송되지 않았으므로 삭제 (기존 여행 상세 경로에서 삭제한 경우임)
		for (Long id : existingDetailsMap.keySet()) {
			deleteExistingPathDetail(id);
		}
	}

	@Override
	public List<PathDetailWithAttractionDto> getPathDetailsWithAttraction(Long pathId) {
		// 기존 상세 경로를 가져옴
		return pathMapper.getPathDetailsWithAttraction(pathId);
	}

	private void createNewPathDetail(Long pathId, PathDetailInsertRequestDto newPathDetail) {
		PathDetailDto pathDetail = PathDetailDto.builder().orders(newPathDetail.getOrders()).day(newPathDetail.getDay())
				.contentId(newPathDetail.getContentId()).pathId(pathId).build();
		pathMapper.createPathDetail(pathDetail);
	}

	private void updateExistingPathDetail(PathDetailInsertRequestDto existingDetail) {
		PathDetailDto pathDetail = PathDetailDto.builder().id(existingDetail.getId()).orders(existingDetail.getOrders())
				.day(existingDetail.getDay()).contentId(existingDetail.getContentId()).build();

		pathMapper.updatePathDetail(pathDetail);
	}

	private void deleteExistingPathDetail(Long id) {
		// 기존 상세 경로 삭제
		pathMapper.deletePathDetail(id);
	}

	@Override
	public List<AreacodeDto> getSido() {
		// TODO Auto-generated method stub
		return pathMapper.getSido();
	}

	@Override
	public List<AreacodeDto> getGugun(int sido) {
		// TODO Auto-generated method stub
		return pathMapper.getGugun(sido);
	}

	@Override
	public void updateHit(Long pathId) {
		pathMapper.updateHit(pathId);

	}

}
