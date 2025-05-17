package com.eucl.controllers;

import com.eucl.dto.request.PurchaseTokenCreateDTO;
import com.eucl.dto.response.PurchaseTokenResponseDTO;
import com.eucl.model.PurchasedToken;
import com.eucl.services.PurchaseTokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/token")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PurchaseTokenController {
    private final PurchaseTokenService purchaseTokenService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseTokenResponseDTO> purchase(@Valid @RequestBody PurchaseTokenCreateDTO purchaseTokenCreateDTO) throws BadRequestException {
        return ResponseEntity.ok(purchaseTokenService.create(purchaseTokenCreateDTO));
    }

    @GetMapping("/search/{meterNumber}")
    public ResponseEntity<List<PurchasedToken>> search(@PathVariable("meterNumber") String meterNumber) {
        return ResponseEntity.ok(purchaseTokenService.searchTokenByMeterNumber(meterNumber));
    }
}


