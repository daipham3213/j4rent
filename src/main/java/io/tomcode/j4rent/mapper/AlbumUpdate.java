package io.tomcode.j4rent.mapper;

import io.tomcode.j4rent.core.entities.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumUpdate {

    Album Album;

    AlbumCreate albumCreate;
}
