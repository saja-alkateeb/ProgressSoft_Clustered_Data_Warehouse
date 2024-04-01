package com.clustereddatawarehouse;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class FxDealRequestDetails {

    @NotNull
    @Positive(message = "The deal id must be positive number")
    private Long dealId;

    @NotBlank(message = "The currency code is required")
    @Pattern(regexp = "^[a-zA-Z]{3}$")
    private String fromCurrencyIsoCode;

    @NotBlank(message = "The currency code is required")
    @Pattern(regexp = "^[a-zA-Z]{3}$")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimeStamp;

    @NotNull
    @Positive(message = "The deal amount must be positive")
    private BigDecimal dealAmountInOrderingCurrency;
}
