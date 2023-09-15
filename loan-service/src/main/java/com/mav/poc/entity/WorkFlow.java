package com.mav.poc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "WORKFLOW")
@Entity
@Data
public class WorkFlow {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "loanId")
    private String loanId;
    @Column(name = "status")
    private String status;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "updatedBy")
    private String updatedBy;
    @Column(name = "createdTS")
    private Date createdTS;
    @Column(name = "updatedTS")
    private Date updatedTS;

}

