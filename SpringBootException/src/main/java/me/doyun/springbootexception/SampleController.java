package me.doyun.springbootexception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        throw new SampleException();
    }

    @ExceptionHandler(SampleException.class)    //SampleException이 발생하면 이 핸들러를 사용하겠다는 의미\
    public @ResponseBody AppError sampleError(SampleException e) {  //AppError라는 클래스를 만들어 멤버변수를 정의해주고 에러처리할때 사용
        AppError appError = new AppError();
        appError.setMessage("error.app.key");
        appError.setReason("I don't know...");
        return appError;
    }

}
