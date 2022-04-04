package io.tomcode.j4rent.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumLoad {
    String name;

    String is_hidden;

    List<ImageLoad> imageLoadList;
}
