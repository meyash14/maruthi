package com.mav.poc.repo;

import com.mav.poc.entity.WorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkFlowRepository extends JpaRepository<WorkFlow, String> {

}
