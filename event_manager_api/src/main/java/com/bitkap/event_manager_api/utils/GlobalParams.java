package com.bitkap.event_manager_api.utils;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote Interface use to preset some values.
 */
public interface GlobalParams {
    String API_PREFIX = "/api/";

    enum ResponseStatusEnum {
        SUCCESS,
        ERROR,
        WARNING,
        NO_ACCESS,
    }
}
