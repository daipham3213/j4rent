package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageCreate;
import org.springframework.stereotype.Component;

@Component
public interface IImageService {
    void upImage(ImageCreate file) throws ImageFailException;
}
