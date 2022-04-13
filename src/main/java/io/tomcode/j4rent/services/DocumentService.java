package io.tomcode.j4rent.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.DocumentRepository;
import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.services.IDocumentService;
import io.tomcode.j4rent.mapper.DocumentCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("documentService")
public class DocumentService implements IDocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper mapper;

    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper, ObjectMapper mapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }


    @Override
    public Document createDocument(Document Document) {
        return documentRepository.save(Document);
    }

    @Override
    public Document createDocument(DocumentCreate Document) {
        return documentRepository.save((new Document(Document)));
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
        } catch (JsonProcessingException e) {
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
        String json = mapper.writeValueAsString(object);
        return mapper.readTree(json);
    }

    @Override
    public Document getDocument(UUID documentId) {
        return documentRepository.findDocumentById(documentId);
    }

    @Override
    public Page<PostDetails> getPostCreatedInDocument(Pageable page, UUID uuid) {
        List<Document> list = documentRepository.findByCreatedByIdIsAndDocumentCodeEquals(uuid, "post");
        List<PostDetails> postDetails = new ArrayList<>();

        for (Document document : list
        ) {
            Post post = mapper.convertValue(document.getData(), Post.class);
            postDetails.add(modelMapper.map(post, PostDetails.class));
        }
        return new PageImpl<>(postDetails, page, page.getPageSize());


    }

}
