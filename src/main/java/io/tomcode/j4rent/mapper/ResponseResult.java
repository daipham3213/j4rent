package io.tomcode.j4rent.mapper;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseResult {
    private String error;
    private Object data;
    private int status;

    public ResponseResult(HttpStatus status, String error, Object data) {
        this.error = error;
        this.data = data;
        this.status = status.value();
    }

    public ResponseResult(HttpStatus status, String error) {
        this.status = status.value();
        this.error = error;
    }

    public ResponseResult(HttpStatus status, Object data) {

    }
}
