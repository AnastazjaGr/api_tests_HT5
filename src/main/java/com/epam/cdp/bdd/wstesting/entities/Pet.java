package com.epam.cdp.bdd.wstesting.entities;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pet {
    private long id;
    private String name;
    private String status;
}