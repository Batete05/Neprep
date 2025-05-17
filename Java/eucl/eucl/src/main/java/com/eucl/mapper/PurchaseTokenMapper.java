package com.eucl.mapper;


import com.eucl.dto.response.PurchaseTokenResponseDTO;
import com.eucl.model.PurchasedToken;
import org.springframework.stereotype.Service;

@Service
public class PurchaseTokenMapper {



    public PurchaseTokenResponseDTO toPurchasedTokenDTO(PurchasedToken token) {


        return PurchaseTokenResponseDTO.builder()
                .purchaseDate(token.getPurchaseDate())
                .token(token.getToken())
                .tokenStatus(token.getTokenStatus())
                .tokenValueDays(token.getTokenValueDays())
                .amount(token.getAmount())
                .build();
    }
}
