package io.tomcode.j4rent.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.DocumentRepository;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.IDocumentService;
import io.tomcode.j4rent.core.services.IOTPService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.exception.DocumentIsNotFoundException;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.DocumentCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service("documentService")
public class DocumentService implements IDocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper mapper;
    private final IOTPService otpService;

    private final IAlbumService albumService;


    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper, ObjectMapper mapper, IOTPService otpService, IAlbumService albumService) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.otpService = otpService;
        this.albumService = albumService;
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
            document.setRegister(true);
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
    public Document getDocument(UUID documentId, String code) throws DocumentIsNotFoundException {
        Document document = documentRepository.findByIdEqualsAndDocumentCodeEquals(documentId, code);
        if (document == null) throw new DocumentIsNotFoundException();
        return document;
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

    @Override
    public void deleteOTPAndDocument(UUID uuid) {
        Document document = documentRepository.findDocumentById(uuid);
        otpService.cleanOTP(document.getId());
        documentRepository.deleteById(document.getId());
    }

    @Override
    public void deleteDocumentById(UUID uuid) {
        documentRepository.deleteById(uuid);
    }

    @Override
    public void updateCreatedByDocument(UUID idDocument, UUID userId) {
        Document document = documentRepository.findDocumentById(idDocument);
        document.setCreatedById(userId);
        documentRepository.save(document);
    }


}
