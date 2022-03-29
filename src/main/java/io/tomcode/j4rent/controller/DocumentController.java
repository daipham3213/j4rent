package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.services.IDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Document> createDocument(@RequestBody Account account) {
        try {
//            return new ResponseEntity<>(documentService.createDocument(account),HttpStatus.CREATED);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
