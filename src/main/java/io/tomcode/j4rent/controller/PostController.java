package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.mapper.PostCreate;
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
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService, IAccountService accountService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody PostCreate post) {
        try {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, null, postService.createPost(post)), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/created")
    public ResponseEntity<ResponseResult> getCreatedPosts(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "floorArea", required = false, defaultValue = "0") int floorArea,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "300000") int maxPrice) {
        try {

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
            Page<PostDetails> allPost;
            if (minPrice != 0 || maxPrice != 300000 || floorArea != 0) {
                if (floorArea == 0) floorArea = 500;
                if (maxPrice == 300000 && minPrice == 0) maxPrice = 5000000;

                allPost = postService.getCreatedPosts(pageable, floorArea, minPrice, maxPrice);
            } else allPost = postService.getCreatedPosts(pageable);

            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<ResponseResult> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "floorArea", required = false, defaultValue = "500") int floorArea,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "300000") int maxPrice) {
        try {

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
            Page<PostDetails> allPost = postService.getAllPost(pageable);
            if (minPrice != 0 || maxPrice != 300000 || floorArea != 500) {
                if (maxPrice == 300000 && minPrice == 0) maxPrice = 5000000;

                allPost = postService.getAllPost(pageable, floorArea, minPrice, maxPrice);
            }
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
