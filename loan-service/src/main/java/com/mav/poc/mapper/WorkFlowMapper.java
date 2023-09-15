package com.mav.poc.mapper;

import com.mav.poc.entity.WorkFlow;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class WorkFlowMapper {

    private static final String CREATED_BY = "emp123@axis.com";

    public WorkFlow toWorkFlow(String loanId) {
        WorkFlow workFlow = new WorkFlow();
        workFlow.setId(UUID.randomUUID().toString());
        workFlow.setLoanId(loanId);
        workFlow.setStatus("START");
        workFlow.setCreatedBy(CREATED_BY);
        workFlow.setCreatedTS(new Date());
        return workFlow;
    }
}
