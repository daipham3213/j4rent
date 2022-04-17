package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Album;

import java.util.UUID;

public interface AlbumRepository extends BaseRepository<Album, UUID> {
    Album findAlbumById(UUID uuid);
}