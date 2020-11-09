package com.test.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    BranchDAO branchDAO;

    public List<BranchDTO> getList() {
        return branchDAO.getList();
    }


    public BranchDTO getBranch(String id) {
        return branchDAO.getBranch(id);
    }
}
