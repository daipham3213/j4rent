package io.tomcode.j4rent.core.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public interface ICloudinaryService {
    Map upload(MultipartFile multipartFile) throws IOException;

    Map delete(String id) throws IOException;
}
