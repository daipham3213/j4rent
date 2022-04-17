package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.mapper.PostDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DocumentRepository extends BaseRepository<Document, UUID> {
    Document findDocumentById(UUID uuid);

    List<Document> findByCreatedByIdIsAndDocumentCodeEquals(UUID createdById, String documentCode);

    void deleteAllByCreatedByIdAndDocumentCode( UUID id,String documentCode );

    @Override
    void deleteById(UUID uuid);

    //    @Modifying
//    @Query(value = "delete ")
    void deleteAllByDocumentCode(String code);



}