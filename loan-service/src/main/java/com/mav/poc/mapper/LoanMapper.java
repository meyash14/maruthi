package com.mav.poc.mapper;

import com.mav.poc.entity.Loan;
import com.mav.poc.model.LoanDTO;
import com.mav.poc.util.ServiceUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class LoanMapper {
    public Loan toLoan(Loan loan, LoanDTO loanDTO) {
        if (loan == null) {
            loan = new Loan();
            loan.setId(UUID.randomUUID().toString());
            loan.setActive(true);
        }
        loan.setCustomerId(loanDTO.getCustomerId());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setBranchCode(loanDTO.getBranchCode());
        loan.setAadhaarNumber(loanDTO.getAadhaarNumber());
        loan.setBirthDate(loanDTO.getBirthDate());
        loan.setEmailId(loanDTO.getEmailId());
        loan.setFirstName(loanDTO.getFirstName());
        loan.setMiddleName(loanDTO.getMiddleName());
        loan.setLastName(loanDTO.getLastName());
        loan.setGender(loanDTO.getGender());
        loan.setMobileNo(loanDTO.getMobileNo());
        loan.setPanNumber(loanDTO.getPanNumber());
        return loan;
    }

    public void setCreatedAuditInfo(Loan loan) {
        loan.setCreatedBy(ServiceUtils.getUserDetails().getUsername());
        loan.setCreatedTS(new Date());
    }

    public void setUpdatedAuditInfo(Loan loan) {
        loan.setCreatedBy(ServiceUtils.getUserDetails().getUsername());
        loan.setUpdatedTS(new Date());
    }

    public LoanDTO toLoan(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .customerId(loan.getCustomerId())
                .loanType(loan.getLoanType())
                .branchCode(loan.getBranchCode())
                .aadhaarNumber(loan.getAadhaarNumber())
                .birthDate(loan.getBirthDate())
                .emailId(loan.getEmailId())
                .firstName(loan.getFirstName())
                .middleName(loan.getMiddleName())
                .lastName(loan.getLastName())
                .gender(loan.getGender())
                .mobileNo(loan.getMobileNo())
                .panNumber(loan.getPanNumber())
                .active(loan.getActive())
                .createdBy(loan.getCreatedBy())
                .updatedBy(loan.getUpdatedBy())
                .createdTS(loan.getCreatedTS())
                .updatedTS(loan.getUpdatedTS())
                .build();
    }
}
