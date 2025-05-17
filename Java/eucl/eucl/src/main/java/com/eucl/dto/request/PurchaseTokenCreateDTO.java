package com.eucl.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PurchaseTokenCreateDTO {

    @Size(min = 6, max = 6)
    @Pattern(regexp = "^\\d+$", message = "Value must contain digits only")
    private String meterNumber;

    @Min(100)
    @Max(365 * 5 * 100)
    private Integer amount;



}
