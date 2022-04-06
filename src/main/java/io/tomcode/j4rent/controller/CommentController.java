package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.services.ICommentService;
import io.tomcode.j4rent.mapper.CommentCreate;
import io.tomcode.j4rent.mapper.ResponseResult;
import liquibase.pro.packaged.E;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            Comment newComment = commentService.createComment(comment);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", newComment), HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
