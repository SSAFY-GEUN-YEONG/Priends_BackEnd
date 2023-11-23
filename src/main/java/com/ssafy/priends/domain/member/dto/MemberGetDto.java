package com.ssafy.priends.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberGetDto {
    String email;
    String nickname;
    String image;
    String alarm;
    Long id;
}
