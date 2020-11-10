package com.test.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public void validate(HttpServletRequest request, HttpServletResponse response, CustomerDTO dto) {

        int result = 0;

        //아이디 넘겨서 유저정보 가져오기
        CustomerDTO customerDTO = customerDAO.getCustomerDTO(dto.getName());

        //패스워드 일치하는지 확인
        try {
            if (dto.getPassword().equals(customerDTO.getPassword())) {
                    response.sendRedirect("/list");
            } else {
                printError(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            printError(request, response);
            e.printStackTrace();
        }

    }

    private void printError(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();

            writer.print("<html>");
            writer.print("<head>");
            writer.print("<meta charset='UTF-8'>");
            writer.print("</head>");
            writer.print("<body>");
            writer.print("<script>");
            writer.print("alert('아이디와 비밀번호를 확인하세요.'); history.back();");
            writer.print("</script>");
            writer.print("</body>");
            writer.print("</html>");

            writer.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}

