package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;


    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody Post post)
    {
        try {
            postService.createPost(post);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK,"","Post created"),HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
