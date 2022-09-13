package com.example.study_admin.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신시간
    //@JsonProperty("transaction_time") 해당 이름으로 JSON 내 변수명을 지정 가능하다.
    // 그러나 application.properties 에 spring.jackson.property-naming-strategy=SNAKE_CASE를 입력하면 자동으로 모두 처리된다.
    private LocalDateTime transactionTime; // ISO, YYYY-MM-DD hh:mm:ss 등 다양한 포맷이 있다.

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;


    private T data;

    // OK
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
