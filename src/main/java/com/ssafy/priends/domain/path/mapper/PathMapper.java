package com.ssafy.priends.domain.path.mapper;

import com.ssafy.priends.domain.path.dto.PathDetailDto;
import com.ssafy.priends.domain.path.dto.PathDto;
import com.ssafy.priends.domain.path.dto.PathGetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PathMapper {
    void createPath(PathDto pathDto);

    List<PathGetDto> getPathList(Long memberId);

    void updatePath(PathDto PathDto);

    void deletePath(Long pathId);

    // 여행 경로 상세 부분
    void createListPathDetails(List<PathDetailDto> pathDetailDtoList);

    List<PathDetailDto> getPathDetails(Long pathId);

    void createPathDetail(PathDetailDto pathDetail);

    void updatePathDetail(PathDetailDto pathDetail);

    void deletePathDetail(Long id);
}
