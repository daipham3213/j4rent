package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.services.IDocumentService;
import io.tomcode.j4rent.mapper.DocumentCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class DocumentController {

    private final IDocumentService documentService;


    public DocumentController(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Document>> getAll() {
        return new ResponseEntity<>(documentService.getAllDocument(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> createDocument(@RequestBody DocumentCreate documentCreate) {
        try {
            Document document = documentService.createDocument(documentCreate);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", document.getData()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/postCreated")
    public ResponseEntity<ResponseResult> getPostCreated(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "id", required = false) UUID id
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
            Page<PostDetails> allPost = documentService.getPostCreatedInDocument(pageable, id);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/detele")
    public ResponseEntity<ResponseResult> deteleDocument(@RequestBody UUID id){
        try {
            documentService.deleteOTPAndDocument(id);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "Deteled document"), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}