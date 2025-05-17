package com.eucl.controllers;

import com.eucl.dto.request.MeterCreateDTO;
import com.eucl.exceptions.CustomRequestException;
import com.eucl.model.Meter;
import com.eucl.services.MeterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meter")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("")
    public ResponseEntity<Meter> addMeter(@Valid @RequestBody MeterCreateDTO meterCreateDTO) throws CustomRequestException {
        return ResponseEntity.ok(meterService.createMeter(meterCreateDTO));
    }
}
