package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumCreate;
import io.tomcode.j4rent.mapper.AlbumUpdate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IAlbumService {

    Album getAlbumById(UUID id);

    Album createAlbum(AlbumCreate albumCreate) throws ImageFailException;

    Album updateAlbum(AlbumUpdate albumCreate) throws ImageFailException;

}
