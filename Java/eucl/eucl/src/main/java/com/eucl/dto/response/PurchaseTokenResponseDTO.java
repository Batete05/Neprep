package com.eucl.dto.response;


import com.eucl.model.TokenStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PurchaseTokenResponseDTO {

    private String token;
    private TokenStatus tokenStatus;
    private LocalDateTime purchaseDate;
    private int amount;
    private int tokenValueDays;
}
