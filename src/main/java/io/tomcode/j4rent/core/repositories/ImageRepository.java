package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}