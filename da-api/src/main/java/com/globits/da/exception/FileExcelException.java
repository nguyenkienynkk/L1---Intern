package com.globits.da.exception;

import com.globits.da.validation.ErrorValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileExcelException extends RuntimeException{
    private  final ErrorValidation errorValidation;
    public FileExcelException(ErrorValidation errorValidation){
        super(errorValidation.getErrorMessage());
        this.errorValidation = errorValidation;
    }
}
