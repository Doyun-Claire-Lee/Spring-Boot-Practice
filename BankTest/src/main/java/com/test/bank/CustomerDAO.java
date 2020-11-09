package com.test.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

//    @Autowired
//    private SqlsSessionTemplate sqlsSessionTemplate;

    public CustomerDTO getCustomerDTO(String name) {

        //DB에서 Customer 정보 가져오기(where name = name)
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1);
        dto.setName("4value");
        dto.setPassword("1234");

        return dto;

    }
}
