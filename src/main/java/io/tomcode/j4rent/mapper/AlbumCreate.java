package io.tomcode.j4rent.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumCreate {
    String name;

    Boolean isHidden;

    List<ImageCreate> imageLoadList;
}
