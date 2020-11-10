package com.test.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BankController {

    @Autowired
    CustomerService customerService;
    @Autowired
    BranchService branchService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginok")
    public void loginok(HttpServletRequest request,
                        HttpServletResponse response,
                        String name,
                        String password) {

        CustomerDTO dto = new CustomerDTO();
        dto.setName(name);
        dto.setPassword(password);

        customerService.validate(request, response, dto);
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("branchList", branchService.getList());

        return "list";
    }

    @GetMapping("/detail")
    public String detail(Model model, String id) {

        model.addAttribute("branchDTO", branchService.getBranch(id));
        return "detail";
    }

}
