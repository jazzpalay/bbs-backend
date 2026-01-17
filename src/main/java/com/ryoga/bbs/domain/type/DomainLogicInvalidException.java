package com.ryoga.bbs.domain.type;

public class DomainLogicInvalidException extends RuntimeException {
    public DomainLogicInvalidException(String message) {
        super(message);
    }
}
