package com.codeaddi.mrc_resources.model.enums;

import lombok.Getter;

@Getter
public enum BoatType {
    // scull
    SINGLE_SCULL("1x", 1, 2),
    DOUBLE_SCULL("2x", 2,4),
    COXLESS_QUAD("4x", 4,8),
    COXED_QUAD("4x+", 5,8),
    OCTUPLE("8x+", 9,16),
    // sweep
    COXLESS_PAIR("2-", 2,2),
    COXLESS_FOUR("4-", 4,4),
    COXED_PAIR("2+", 3,2),
    COXED_FOUR("4+", 5,4),
    COXED_EIGHT("8+", 9,8);

    private final String symbol;
    private final int capacity;
    private final int bladesRequired;

    BoatType(String symbol, int capacity, int bladesRequired) {
        this.symbol = symbol;
        this.capacity = capacity;
        this.bladesRequired = bladesRequired;
    }
}
