package com.globits.da.exception;

import com.globits.da.validation.ErrorValidation;
import lombok.Data;

@Data
public class NotNullException extends RuntimeException {
    private final ErrorValidation errorValidation;
    public NotNullException(ErrorValidation errorValidation){
        super(errorValidation.getErrorMessage());
        this.errorValidation = errorValidation;
    }
}
