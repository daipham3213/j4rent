package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageLoad;
import org.springframework.stereotype.Component;

@Component
public interface IImageService {
    void upload(ImageLoad file) throws ImageFailException;
}
