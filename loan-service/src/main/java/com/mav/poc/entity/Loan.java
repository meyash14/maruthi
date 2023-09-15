package com.mav.poc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "LOAN")
@Entity
@Data
public class Loan {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "loanType")
    private String loanType;
    @Column(name = "branchCode")
    private String branchCode;
    @Column(name = "panNumber")
    private String panNumber;
    @Column(name = "aadhaarNumber")
    private String aadhaarNumber;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "mobileNumber")
    private String mobileNo;
    @Column(name = "birthDate")
    private String birthDate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "emailId")
    private String emailId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "updatedBy")
    private String updatedBy;
    @Column(name = "createdTS")
    private Date createdTS;
    @Column(name = "updatedTS")
    private Date updatedTS;

}

