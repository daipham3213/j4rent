package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageView {

    private String caption;

    private String url;


}
