package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumLoad;
import org.springframework.stereotype.Component;

@Component
public interface IAlbumService {

    Album createAlbum (AlbumLoad albumLoad) throws ImageFailException;
}
