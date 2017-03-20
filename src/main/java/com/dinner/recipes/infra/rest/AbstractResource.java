package com.dinner.recipes.infra.rest;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.util.UUID;

@ToString
@EqualsAndHashCode
public class AbstractResource extends ResourceSupport {

    private String requestUUID = UUID.randomUUID().toString();

    public String getRequestUUID() {
        return requestUUID;
    }
}