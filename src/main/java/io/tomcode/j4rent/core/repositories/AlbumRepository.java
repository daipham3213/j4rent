package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends BaseRepository<Album, UUID> {
    Album findAlbumById(UUID uuid);
}