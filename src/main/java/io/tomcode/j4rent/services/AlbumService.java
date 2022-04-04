package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumLoad;
import io.tomcode.j4rent.mapper.ImageLoad;
import org.springframework.stereotype.Service;

@Service("albumService")
public class AlbumService implements IAlbumService {

    private final ImageService imageService;

    public AlbumService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public Album createAlbum(AlbumLoad albumLoad) throws ImageFailException {
        Album album = new Album(albumLoad.getName(), albumLoad.getIs_hidden());
        for (ImageLoad imageLoad : albumLoad.getImageLoadList()) {
            album.getImages().add(imageService.createImage(imageLoad));
        }   
        return album;

    }
}
