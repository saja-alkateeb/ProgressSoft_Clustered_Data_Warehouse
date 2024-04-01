package com.clustereddatawarehouse;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "fx_deal")
@Entity
public class FxDeal {

    @SequenceGenerator(name = "deal_id_sequence", sequenceName = "deal_id_sequence")
    @GeneratedValue(generator = "deal_id_sequence", strategy = GenerationType.SEQUENCE)
    @Id
    Long id;
    private Long dealId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private LocalDateTime dealTimeStamp;
    private BigDecimal dealAmountInOrderingCurrency;
}
