package com.mav.poc.repo;

import com.mav.poc.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LoanRepository extends JpaRepository<Loan, String> {

    Page<Loan> findAllByCreatedBy(String createdBy, Pageable pageable);

}
