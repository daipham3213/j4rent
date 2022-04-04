package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}