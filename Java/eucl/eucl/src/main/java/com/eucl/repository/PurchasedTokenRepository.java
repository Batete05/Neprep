package com.eucl.repository;

import com.eucl.model.PurchasedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchasedTokenRepository extends JpaRepository<PurchasedToken, UUID> {
    List<PurchasedToken> findAllByMeter_MeterNumber(String meterMeterNumber);

    @Procedure(name = "PurchasedToken.findExpiringTokens")
    List<PurchasedToken> findExpiringTokens();
}
