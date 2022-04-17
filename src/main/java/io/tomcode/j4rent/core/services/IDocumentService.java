package io.tomcode.j4rent.core.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.exception.DocumentIsNotFoundException;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.DocumentCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IDocumentService {
    Document createDocument(Document Document);

    Document createDocument(DocumentCreate Document);

    Document createDocument(String documentCode, Object data);

    Iterable<Document> getAllDocument();

    JsonNode obj2Json(Object object) throws JsonProcessingException;

    Document getDocument(UUID documentId);

    Document getDocument(UUID documentId,String code) throws DocumentIsNotFoundException;

    Page<PostDetails> getPostCreatedInDocument(Pageable pageable, UUID uuid);

    void deleteOTPAndDocument(UUID uuid);

    void deleteDocumentById(UUID uuid);

    void updateCreatedByDocument(UUID idDocument, UUID userId);



}
