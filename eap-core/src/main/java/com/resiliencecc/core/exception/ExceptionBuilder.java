package com.resiliencecc.core.exception;

import com.resiliencecc.api.exception.BusinessException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ExceptionBuilder implements Serializable {

    private static final long serialVersionUID = -8956052769465382551L;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/validation");

    private final BusinessException exception;

    public ExceptionBuilder() {
        this.exception = new BusinessException();
    }

    public ExceptionBuilder bind(final String code, final Object... params) {
        String message = resourceBundle.getString(code);

        if (params != null && params.length > 0) {
            MessageFormat format = new MessageFormat(message);
            message = format.format(Arrays.stream(params)
                    .map(String::valueOf).toArray(String[]::new));
        }

        this.exception.setMessage(message);
        return this;
    }

    public BusinessException build() {
        return this.exception;
    }

}
