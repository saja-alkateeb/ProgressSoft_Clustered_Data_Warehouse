package com.clustereddatawarehouse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class FxDealService {
    private final FxDealRepo fxDealRepo;

    public void persistFxDeal(List<FxDealRequestDetails> dealsDetails) {
        log.info("Processing fx deals...");
        for (FxDealRequestDetails dealDetails : dealsDetails) {
            if (!fxDealRepo.existsFxDealByDealId(dealDetails.getDealId())) {
                FxDeal deal = FxDeal.builder()
                        .dealId(dealDetails.getDealId())
                        .dealTimeStamp(dealDetails.getDealTimeStamp())
                        .dealAmountInOrderingCurrency(dealDetails.getDealAmountInOrderingCurrency())
                        .fromCurrencyIsoCode(dealDetails.getFromCurrencyIsoCode())
                        .toCurrencyIsoCode(dealDetails.getToCurrencyIsoCode())
                        .build();

                fxDealRepo.save(deal);
                log.info(String.format("Deal with id: %d is persisted", deal.getDealId()));
            } else {
                log.info(String.format("Deal with ID: %s is already exist!.", dealDetails.getDealId()));
            }
        }
        log.info("Fx deals are processed successfully");
    }
}
