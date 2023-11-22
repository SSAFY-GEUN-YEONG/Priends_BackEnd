package com.ssafy.priends.domain.path.mapper;

import com.ssafy.priends.domain.attraction.dto.AreacodeDto;
import com.ssafy.priends.domain.path.dto.PathDetailDto;
import com.ssafy.priends.domain.path.dto.PathDetailWithAttractionDto;
import com.ssafy.priends.domain.path.dto.PathDto;
import com.ssafy.priends.domain.path.dto.PathGetDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PathMapper {
	void createPath(PathDto pathDto);

	List<PathGetDto> getPathList(Long pathId, String city, int order, int limitCount);

	List<PathGetDto> getMyPathList(Long memberId);

	void updatePath(PathDto PathDto);

	void deletePath(Long pathId);

	// 여행 경로 상세 부분
//    void createListPathDetails(List<PathDetailDto> pathDetailDtoList);

	List<PathDetailDto> getSimplePathDetails(Long pathId);

	void createPathDetail(PathDetailDto pathDetail);

	void updatePathDetail(PathDetailDto pathDetail);

	void deletePathDetail(Long id);

	List<PathDetailWithAttractionDto> getPathDetailsWithAttraction(Long pathId);

	List<AreacodeDto> getSido();

	List<AreacodeDto> getGugun(int sido);

	void updateHit(Long pathId) ;
 

}
