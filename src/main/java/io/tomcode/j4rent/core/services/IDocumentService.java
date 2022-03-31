package io.tomcode.j4rent.core.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.tomcode.j4rent.core.entities.Document;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IDocumentService {
    Document createDocument(Document document);

    Document createDocument(String documentCode, Object data);

    Iterable<Document> getAllDocument();

    JsonNode obj2Json(Object object) throws JsonProcessingException;

    Document getDocument(UUID documentId);


}
