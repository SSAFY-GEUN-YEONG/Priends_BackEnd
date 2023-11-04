package com.ssafy.priends.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {
    Long id;
    String email;
    String name;
    String alarm;
    String role;
}
