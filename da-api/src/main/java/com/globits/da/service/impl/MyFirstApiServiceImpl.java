package com.globits.da.service.impl;

import com.globits.da.domain.Employee;
import com.globits.da.service.MyFirstApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFirstApiServiceImpl implements MyFirstApiService {
    @Override
    public String myFirstApiService() {
        return "myFirstApiService";
    }
}
