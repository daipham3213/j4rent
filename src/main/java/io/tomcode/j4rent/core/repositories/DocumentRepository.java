package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends BaseRepository<Document, UUID> {
    Document findByIdEqualsAndDocumentCodeEquals(UUID id, String documentCode);
    Document findDocumentById(UUID uuid);

    List<Document> findByCreatedByIdIsAndDocumentCodeEquals(UUID createdById, String documentCode);

    void deleteAllByCreatedByIdAndDocumentCode(UUID id, String documentCode);

    @Override
    void deleteById(UUID uuid);

    void deleteAllByDocumentCode(String code);


}