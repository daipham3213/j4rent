package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.services.IAccountService;
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
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class PostController {
    private final IPostService postService;
    private final IAccountService accountService;

    public PostController(IPostService postService, IAccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody PostDetails post) {
        try {
            postService.createPost(post);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, null, "Post created!"), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @GetMapping("/created")
//    public ResponseEntity<ResponseResult> getCreatedPosts(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", defaultValue = "") String sortBy,
//            @RequestParam(name = "floorArea", required = false, defaultValue = "0") int floorArea,
//            @RequestParam(name = "mix", required = false, defaultValue = "0") int mix,
//            @RequestParam(name = "max", required = false, defaultValue = "300000") int max
//    ) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<PostDetails> allPosts = postService.getCreatedPosts(pageable);
//        return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, null, allPosts), HttpStatus.OK);
//    }

    @GetMapping("/created")
    public ResponseEntity<ResponseResult> getCreatedPosts(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "floorArea", required = false, defaultValue = "0") int floorArea,
            @RequestParam(name = "mix_price", required = false, defaultValue = "0") int mix_price,
            @RequestParam(name = "max_price", required = false, defaultValue = "300000") int max_price) {
        try {

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
            Page<PostDetails> allPost = postService.getCreatedPosts(pageable);
            if (mix_price != 0 || max_price != 300000 || floorArea != 0) {
                if (floorArea == 0) floorArea = 500;
                if (max_price == 300000 && mix_price == 0) max_price = 5000000;

                allPost = postService.getCreatedPosts(pageable, floorArea, mix_price, max_price);
            }
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<ResponseResult> a(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "floorArea", required = false, defaultValue = "0") int floorArea,
            @RequestParam(name = "mix_price", required = false, defaultValue = "0") int mix_price,
            @RequestParam(name = "max_price", required = false, defaultValue = "300000") int max_price) {
        try {

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
            Page<PostDetails> allPost = postService.getAllPost(pageable);
            if (mix_price != 0 || max_price != 300000 || floorArea != 0) {
                if (floorArea == 0) floorArea = 500;
                if (max_price == 300000 && mix_price == 0) max_price = 5000000;

                allPost = postService.getAllPost(pageable,floorArea ,mix_price, max_price);
            }
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
