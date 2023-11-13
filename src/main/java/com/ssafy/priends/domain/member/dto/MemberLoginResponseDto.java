package com.ssafy.priends.domain.member.dto;

import com.ssafy.priends.global.component.jwt.dto.TokenDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class MemberLoginResponseDto {
    private TokenMemberInfoDto memberInfo;
    private TokenDto token;
}
