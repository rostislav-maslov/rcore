package com.rcore.adapter.domain.phoneNumberFormat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhoneNumberFormatDTO {
    private String code;
    private Integer digitsQuantity;
    private Integer digitsQuantityWithCode;
    private List<Integer> allowedFirstDigits = new ArrayList<>();
}
