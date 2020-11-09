package com.test.bank;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BranchDAO {

    public List<BranchDTO> getList() {
        
        //지점 리스트 담기
        List<BranchDTO> branchList = new ArrayList<BranchDTO>();
        BranchDTO dto = new BranchDTO();

        dto.setId(1);
        dto.setName("강남파이낸스종합금융센터");
        dto.setAddress1("서울특별시 강남구 테헤란로");
        dto.setBusiness_hours("평일 09:00~16:00");
        branchList.add(dto);

        BranchDTO dto2 = new BranchDTO();
        dto2.setId(2);
        dto2.setName("역삼역");
        dto2.setAddress1("서울특별시 강남구 논현로");
        dto2.setBusiness_hours("평일 09:00~16:00");
        branchList.add(dto2);

        return branchList;
    }

    public BranchDTO getBranch(String id) {

        BranchDTO branchDTO = new BranchDTO();

        //id로 branch select 해오기
        branchDTO.setId(1);
        branchDTO.setName("강남파이낸스종합금융센터");
        branchDTO.setAddress1("서울특별시 강남구 테헤란로");
        branchDTO.setAddress2("123 포스코타워");
        branchDTO.setBusiness_hours("평일 09:00~16:00");
        branchDTO.setPhone_number("02-554-8002");
        
        
        return branchDTO;
    }
}
