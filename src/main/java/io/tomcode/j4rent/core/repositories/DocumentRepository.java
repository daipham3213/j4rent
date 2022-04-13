package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.mapper.PostDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Document findDocumentById(UUID uuid);

    List<Document> findByCreatedByIdIsAndDocumentCodeEquals(UUID createdById, String documentCode);





}