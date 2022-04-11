package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.mapper.AlbumCreate;
import io.tomcode.j4rent.mapper.AlbumView;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class AlbumController {

    private final IAlbumService albumService;

    public AlbumController(IAlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody AlbumCreate album) {
        try {
            Album newAlbum = albumService.createAlbum(album);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, null, newAlbum), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
