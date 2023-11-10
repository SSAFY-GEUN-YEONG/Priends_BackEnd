package com.ssafy.priends.domain.member.dto;


import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class MemberLoginActiveDto {
    private Long id;
    private String email;
    private String name;
    private boolean alarm;
    private String role;

    public static MemberLoginActiveDto from(TokenMemberInfoDto info) {
        return MemberLoginActiveDto.builder()
                .id(info.getId())
                .email(info.getEmail())
                .name(info.getName())
                .alarm(info.isAlarm())
                .role(info.getRole())
                .build();
    }
}
