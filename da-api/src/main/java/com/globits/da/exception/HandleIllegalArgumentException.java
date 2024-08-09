package com.globits.da.exception;

import com.globits.da.validation.ErrorValidation;
import lombok.Data;

@Data
public class HandleIllegalArgumentException extends RuntimeException {
    private final ErrorValidation errorValidation;
    public HandleIllegalArgumentException(ErrorValidation errorValidation){
        super(errorValidation.getErrorMessage());
        this.errorValidation = errorValidation;
    }
}
