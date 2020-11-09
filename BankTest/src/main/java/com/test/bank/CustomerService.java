package com.test.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    public int validate(CustomerDTO dto) {

        int result = 0;

        //아이디 넘겨서 유저정보 가져오기
        CustomerDTO customerDTO = customerDAO.getCustomerDTO(dto.getName());

        //패스워드 일치하는지 확인
        if (dto.getPassword().equals(customerDTO.getPassword())) {
            result = 1;
        }

        return result;
    }

    public void printError(HttpServletRequest request, HttpServletResponse response) {
    }
}
