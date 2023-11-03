package com.ssafy.priends.global.common.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Message<T> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    private static class DataHeader {
        private int successCode;
        private String resultCode;
        private String resultMessage;

        private static DataHeader success() {
            return DataHeader.builder()
                    .successCode(0)
                    .build();
        }

        private static DataHeader success(String code, String resultMessage) {
            return DataHeader.builder()
                    .successCode(0)
                    .resultCode(code)
                    .resultMessage(resultMessage)
                    .build();
        }

        private static DataHeader fail(String resultCode, String resultMessage) {
            return DataHeader.builder()
                    .successCode(1)
                    .resultMessage(resultCode)
                    .resultMessage(resultMessage)
                    .build();
        }
    }

    private DataHeader dataHeader;
    private T dataBody;

    public static <T> Message<T> success(T dataBody) {
        return Message.<T>builder()
                .dataHeader(DataHeader.success())
                .dataBody(dataBody)
                .build();
    }

    public static <T> Message<T> success(T dataBody, String code, String resultMessage) {
        return Message.<T>builder()
                .dataHeader(DataHeader.success(code, resultMessage))
                .dataBody(dataBody)
                .build();
    }

    public static Message<Void> success() {
        return Message.<Void>builder()
                .dataHeader(DataHeader.success())
                .build();
    }

    public static <T> Message<T> fail(String resultCode, String resultMessage) {
        return Message.<T>builder()
                .dataHeader(DataHeader.fail(resultCode, resultMessage))
                .dataBody(null)
                .build();
    }
}
