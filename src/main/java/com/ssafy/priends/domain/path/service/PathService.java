package com.ssafy.priends.domain.path.service;

import com.ssafy.priends.domain.path.dto.*;

import java.util.List;

public interface PathService {
    // 여행 경로 생성
    Long createPath(PathInsertRequestDto pathInsertRequestDto);

    // 해당 회원의 여행 경로 불러오기
    List<PathGetDto> getPathList(Long memberId);

    // 여행 경로 수정
    void updatePath(PathUpdateRequestDto pathUpdateRequestDto);

    // 여행 경로 삭제 (클라이언트단에서 보이지 않게끔 처리)
    void deletePath(Long pathId);

    // 여행 경로 상세 생성 (클라이언트단에서 경로 상세 추가 및 삭제시 같이 반영되게끔)
    void createAndUpdatePathDetails(Long pathId, List<PathDetailInsertRequestDto> newPathDetails);

    List<PathDetailDto> getPathDetailList(Long pathId);
}
