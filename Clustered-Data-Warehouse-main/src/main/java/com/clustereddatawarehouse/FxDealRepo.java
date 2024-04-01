package com.clustereddatawarehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepo extends JpaRepository<FxDeal, Long> {
    Boolean existsFxDealByDealId(Long dealId);
}
