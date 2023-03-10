package com.throne.system.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThroneException extends RuntimeException{

    private Integer code;

    private String message;


}
