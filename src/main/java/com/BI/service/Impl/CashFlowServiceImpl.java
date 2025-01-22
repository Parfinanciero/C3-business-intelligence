package com.BI.service.Impl;

import com.BI.dto.ResponseDto.UserExpensesResponse;
import com.BI.service.ICashFlowService;
import org.springframework.stereotype.Service;

@Service
public class CashFlowServiceImpl implements ICashFlowService {

    @Override
    public UserExpensesResponse getExpenses(Long userId) {
        
        return null;
    }
}
