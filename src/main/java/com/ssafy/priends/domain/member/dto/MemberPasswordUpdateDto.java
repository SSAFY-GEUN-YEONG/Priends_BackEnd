package com.ssafy.priends.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberPasswordUpdateDto {
    private Long id;
    private String nowPassword;
    private String changePassword;
}
