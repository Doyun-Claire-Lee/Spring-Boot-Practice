package com.test.bank;

import lombok.Data;

@Data
public class BranchDTO {

    private long id;
    private String name;
    private String address1;
    private String address2;
    private String phone_number;
    private String business_hours;

}
