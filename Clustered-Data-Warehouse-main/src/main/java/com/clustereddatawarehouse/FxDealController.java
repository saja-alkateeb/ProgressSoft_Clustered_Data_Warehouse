package com.clustereddatawarehouse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fx-deal")
public class FxDealController {
    private FxDealService fxDealService;

    @PostMapping
    public ResponseEntity<Void> postFxDeal(
            @RequestBody
            List<@Valid FxDealRequestDetails> fxDealsRequestDetails) {
        fxDealService.persistFxDeal(fxDealsRequestDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
