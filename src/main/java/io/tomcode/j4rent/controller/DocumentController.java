package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.services.IDocumentService;
import io.tomcode.j4rent.mapper.DocumentCreate;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/document")
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
}