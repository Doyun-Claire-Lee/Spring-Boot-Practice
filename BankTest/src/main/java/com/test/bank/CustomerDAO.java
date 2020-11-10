package com.test.bank;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public CustomerDTO getCustomerDTO(String name) {

        //DB에서 Customer 정보 가져오기(where name = name)
        return sqlSessionTemplate.selectOne("customer.getCustomer", name);

    }
}
