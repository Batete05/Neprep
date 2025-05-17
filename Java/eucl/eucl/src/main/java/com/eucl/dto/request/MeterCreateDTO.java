package com.eucl.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MeterCreateDTO {

    @Size(min = 6, max = 6)
    @Pattern(regexp = "^\\d+$", message = "Value must contain digits only")
    private String meterNumber;

    @NotBlank
    private String nationalId;
}
