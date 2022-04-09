package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.services.ICommentService;
import io.tomcode.j4rent.mapper.CommentCreate;
import io.tomcode.j4rent.mapper.ResponseResult;
import liquibase.pro.packaged.E;
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
@RequestMapping("/comment")
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class CommentController {
    private  final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> createComment(@RequestBody CommentCreate comment)
    {
        try {
            CommentCreate newComment = commentService.createComment(comment);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "",newComment ), HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getComment")
    public ResponseEntity<ResponseResult> getCommentById(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                         @RequestParam(name = "id", required = false)UUID uuid)
    {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<CommentCreate> comments = commentService.getComments(pageable,uuid);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", comments), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }
}