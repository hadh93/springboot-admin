package com.example.study_admin.controller;

import com.example.study_admin.model.SearchParam;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd){
        String password = "bbbb";
        System.out.println("id: "+id);
        System.out.println("password: "+pwd);
        return id+pwd;
    }

    // localhost:8080/api/multiParameter?
    //  account=abcd&email=study@gmail.com&page=10 ...
    // 파라미터가 여러 개 있을 때는 이런 방식으로 정보를 받기 어렵다.

    @GetMapping("getMultiParameter")
    public SearchParam getMultiParameter (SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return searchParam;
    }

}
