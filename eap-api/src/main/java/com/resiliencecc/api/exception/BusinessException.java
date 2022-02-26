package com.resiliencecc.api.exception;

import javax.ejb.ApplicationException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApplicationException
public class BusinessException extends Exception {

    private static final long serialVersionUID = -4054493009652021045L;

    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
        this.message = message;
    }

}
