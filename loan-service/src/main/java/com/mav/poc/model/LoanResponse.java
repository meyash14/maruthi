package com.mav.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    @JsonProperty("content")
    List<LoanDTO> content;
    @JsonProperty("pageNo")
    int pageNo;
    @JsonProperty("pageSize")
    int pageSize;
    @JsonProperty("totalElements")
    long totalElements;
    @JsonProperty("totalPages")
    int totalPages;
    @JsonProperty("last")
    boolean last;
}
