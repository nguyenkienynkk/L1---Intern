package com.globits.da.exception;

import com.globits.da.validation.ErrorValidation;
import lombok.Data;

@Data
public class OctException extends RuntimeException{
    private final ErrorValidation errorValidation;
    public OctException(ErrorValidation errorValidation){
        super(errorValidation.getErrorMessage());
        this.errorValidation = errorValidation;
    }
}
