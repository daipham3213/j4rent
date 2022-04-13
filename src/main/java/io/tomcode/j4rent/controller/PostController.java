package io.tomcode.j4rent.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.services.IPostService;
import io.tomcode.j4rent.mapper.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
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
            @RequestParam(name = "floorArea", required = false, defaultValue = "500") int floorArea,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") BigInteger minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "5000000") BigInteger maxPrice) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
            Page<PostDetails> allPost = postService.getCreatedPosts(pageable, floorArea, minPrice, maxPrice);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

//    @GetMapping("")
//    public ResponseEntity<ResponseResult> getAll(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "floorArea", required = false, defaultValue = "500") int floorArea,
//            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
//            @RequestParam(name = "maxPrice", required = false, defaultValue = "5000000") int maxPrice) {
//        try {
//
//            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdDate"));
//            Page<PostDetails> allPost = postService.getAllPost(pageable, floorArea, minPrice, maxPrice);
//            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

    @GetMapping("")
    public ResponseEntity<ResponseResult> getAllPostsWithClosestLocation(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "floorArea", required = false, defaultValue = "500") float floorArea,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") BigInteger minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "5000000") BigInteger maxPrice,
            @RequestParam(name = "distance", required = false, defaultValue = "5") double distance,
            @RequestParam(name = "latitude", required = false, defaultValue = "0") double latitude,
            @RequestParam(name = "longitude", required = false, defaultValue = "0") double longitude
    ) {
        try {

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
            Page<PostDetails> allPost = postService.getAllPost(pageable, floorArea, minPrice, maxPrice, latitude, longitude, distance);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", allPost), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseResult> updatePost(@RequestBody PostUpdate post) {

        try {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", updatePost(post)), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
