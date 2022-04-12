package io.tomcode.j4rent.mapper;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.mapper.AlbumCreate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumUpdate {

    Album Album;

    AlbumCreate albumCreate;
}
