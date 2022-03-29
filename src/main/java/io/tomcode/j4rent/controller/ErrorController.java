package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public ResponseEntity<ResponseResult> error(@RequestBody ResponseStatusException e) {
        return new ResponseEntity<>(new ResponseResult(500, "Error", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
