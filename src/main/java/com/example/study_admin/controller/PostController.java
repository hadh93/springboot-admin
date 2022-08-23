package com.example.study_admin.controller;

import com.example.study_admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    //@PostMapping과 @RequestMapping을 유사하게 사용할 수 있다.
    // 더 정확히는 RequestMapping이 더 범용성 있는 표현?
    // 아래 두가지는 동일한 일을 한다.
    //@RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){

        return searchParam;
    }



    // 주의할 점: POST는 언제 발생하는가? HTML에서 <form>태그를 사용하거나, ajax 검색 등에 사용된다.
    // 때문에 다짜고자 postMethod() 안에 SearchParam searchParam <- 이런 식으로 넣으면 안되고,
    // @RequestBody를 먼저 입력해주어야 한다.
    // 이것은 http post 요청 때 'body'부분에 데이터를 넣어 보내겠다라는 뜻이고,
    // 그 데이터의 형식을 (자료형) SearchParam, 데이터의 내용을 (변수) searchParam에 넣어 보내겠다라는 뜻.

    // 기본적으로 JSON을 사용하기 때문에, 위의 표현을 사용했다.
    // 그러나 xml, multipart-form , text-plain 등 다양한 형태의 데이터를 받는 경우,
    // produces 파라미터를 추가하여 명시해준다.
    // @PostMapping(value = "/postMethod", produces = {application-json})

    // POST는 데이터를 전달받는다는 전제가 있어, YARC! 구글 확장 프로그램을 사용하여 요청 처리를 테스트해본다.




}
