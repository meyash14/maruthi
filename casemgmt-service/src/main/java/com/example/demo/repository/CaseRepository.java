package com.example.demo.repository;

import com.example.demo.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case,String>
    {

    }
