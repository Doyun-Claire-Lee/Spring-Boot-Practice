package com.test.spring;

import com.sun.deploy.net.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping("/index.do")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("data", "테스트 데이터");
        return "index";
    }

    @RequestMapping("/notice/list.do")
    public String noticeList(HttpServletRequest req, HttpServletResponse resp) {
        return "customer/notice/list";
    }
    @RequestMapping("/notice/detail.do")
    public String noticeDetail(HttpServletRequest req, HttpServletResponse resp) {
        return "customer/notice/detail";
    }

}
