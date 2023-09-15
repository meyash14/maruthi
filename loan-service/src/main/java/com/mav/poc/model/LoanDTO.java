package com.mav.poc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("customerId")
    @NotBlank String customerId;
    @JsonProperty("loanType")
    String loanType;
    @JsonProperty("branchCode")
    String branchCode;
    @JsonProperty("panNumber")
    String panNumber;
    @JsonProperty("aadhaarNumber")
    String aadhaarNumber;
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("middleName")
    String middleName;
    @JsonProperty("mobileNumber")
    String mobileNo;
    @JsonProperty("birthDate")
    String birthDate;
    @JsonProperty("gender")
    String gender;
    @JsonProperty("emailId")
    String emailId;
    @JsonProperty("active")
    Boolean active;
    @JsonProperty("createdBy")
    String createdBy;
    @JsonProperty("updatedBy")
    String updatedBy;
    @JsonProperty("createdTS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdTS;
    @JsonProperty("updatedTS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedTS;
}
