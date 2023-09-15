package com.mav.poc.service;

import com.mav.poc.entity.Loan;
import com.mav.poc.mapper.LoanMapper;
import com.mav.poc.mapper.WorkFlowMapper;
import com.mav.poc.model.LoanDTO;
import com.mav.poc.model.LoanResponse;
import com.mav.poc.repo.LoanRepository;
import com.mav.poc.repo.WorkFlowRepository;
import com.mav.poc.util.ServiceUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final WorkFlowRepository workFlowRepository;
    private final LoanMapper loanMapper;
    private final WorkFlowMapper workFlowMapper;

    public ResponseEntity<LoanDTO> createLoanApplication(LoanDTO loanDTO) {
        Loan loan = loanMapper.toLoan(null, loanDTO);
        loanMapper.setCreatedAuditInfo(loan);
        loanRepository.save(loan);
        workFlowRepository.save(workFlowMapper.toWorkFlow(loan.getId()));
        return new ResponseEntity<>(LoanDTO.builder().id(loan.getId()).build(), HttpStatus.CREATED);
    }

    public ResponseEntity<LoanDTO> getLoan(String id) {
        LoanDTO loanDTO = LoanDTO.builder().build();
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()) {
            loanDTO = loanMapper.toLoan(loanOptional.get());
            return new ResponseEntity<>(loanDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(loanDTO, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<LoanDTO> updateLoan(String id, LoanDTO loanDTO) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()) {
            Loan loan = loanMapper.toLoan(loanOptional.get(), loanDTO);
            loanMapper.setUpdatedAuditInfo(loan);
            loanRepository.save(loan);
            loanDTO = loanMapper.toLoan(loan);
            return new ResponseEntity<>(loanDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(loanDTO, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<LoanDTO> deleteLoan(String id) {
        LoanDTO loanDTO = LoanDTO.builder().build();
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setActive(false);
            loanMapper.setUpdatedAuditInfo(loan);
            loanRepository.save(loan);
            return new ResponseEntity<>(loanDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(loanDTO, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<LoanResponse> getAllLoans(
            int pageNo, int pageSize, String sortBy, String sortDir) {
        String createdBy = null;
        UserDetails userDetails = ServiceUtils.getUserDetails();
        if (!userDetails.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
            createdBy = userDetails.getUsername();
        }
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Loan> loans;
        if (StringUtils.isNotBlank(createdBy)) {
            loans = loanRepository.findAllByCreatedBy(createdBy, pageable);
        } else {
            loans = loanRepository.findAll(pageable);
        }
        List<Loan> loanList = loans.getContent();
        List<LoanDTO> content =
                loanList.stream().map(loan -> loanMapper.toLoan(loan)).collect(Collectors.toList());
        LoanResponse loanResponse =
                LoanResponse.builder()
                        .content(content)
                        .pageNo(loans.getNumber() + 1)
                        .pageSize(loans.getSize())
                        .totalElements(loans.getTotalElements())
                        .totalPages(loans.getTotalPages())
                        .last(loans.isLast())
                        .build();
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }
}
