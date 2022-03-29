package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseResult {
    private int status;
    private String error;
    private Object data;



}
