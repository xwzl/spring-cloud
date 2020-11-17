package com.dftc.microservice.health.common.component.region;

public class RegionException extends RuntimeException {

    public RegionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegionException(String message) {
        super(message);
    }
}
