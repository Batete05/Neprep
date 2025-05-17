package com.eucl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(min = 6, max = 6)
    @Pattern(regexp = "^\\d+$", message = "Value must contain digits only")
    @Column(unique = true, nullable = false, length = 6)
    private String meterNumber;

    @Column(nullable = false, unique = true)
    private String nationalId;

}

