package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumCreate;
import io.tomcode.j4rent.mapper.AlbumView;
import org.springframework.stereotype.Component;

@Component
public interface IAlbumService {

    Album createAlbum (AlbumCreate albumLoad) throws ImageFailException;

    Album createAlbum (AlbumView albumView) throws ImageFailException;
}
