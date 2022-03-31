package io.tomcode.j4rent.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.repositories.DocumentRepository;
import io.tomcode.j4rent.core.services.IDocumentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("documentService")
public class DocumentService implements IDocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document createDocument(String documentCode, Object data) {
        try {
            JsonNode json = obj2Json(data);
            Document document = new Document();
            document.setDocumentCode(documentCode);
            document.setData(json);
            document.setWorkflow(false);
            document.setOTP(true);
            return documentRepository.save(document);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    @Override
    public JsonNode obj2Json(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return mapper.readTree(json);
    }

    @Override
    public Document getDocument(UUID documentId) {
        return documentRepository.findDocumentById(documentId);
    }

}
