package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.mapper.PostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import io.tomcode.j4rent.mapper.PostDetails;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;


    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody PostDetails post) {
        try {
            postService.createPost(post);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "Post send to admin"), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getAllPost/")
    public ResponseEntity<ResponseResult> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                 @RequestParam(name = "sort", defaultValue = "") String sortBy,
                                                 @RequestParam(name = "floorArea", required = false, defaultValue = "0") int floorArea,
                                                 @RequestParam(name = "mix", required = false, defaultValue = "0") int mix,
                                                 @RequestParam(name = "max", required = false, defaultValue = "300000") int max) {
        try {

            Pageable pageable = PageRequest.of(page, size);
            Page<PostDetails> allPost = postService.getAllPost(pageable);
            if (sortBy.equals("price")) {
                pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
                allPost = postService.getAllPost(pageable, mix, max);
            }
            if (sortBy.equals("floorArea")){
                pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
                allPost = postService.getAllPost(pageable, floorArea);
            }
            PostResult pageResult = new PostResult(allPost.getContent(), allPost.getSize(), allPost.getPageable().getPageNumber());
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", pageResult), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }


}
