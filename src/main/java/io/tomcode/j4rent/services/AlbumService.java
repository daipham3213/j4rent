package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumCreate;
import io.tomcode.j4rent.mapper.AlbumView;
import io.tomcode.j4rent.mapper.ImageCreate;
import org.springframework.stereotype.Service;

@Service("albumService")
public class AlbumService implements IAlbumService {

    private final ImageService imageService;

    public AlbumService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public Album createAlbum(AlbumCreate albumLoad) throws ImageFailException {
        Album album = new Album(albumLoad.getName(), albumLoad.getIsHidden());
        for (ImageCreate imageLoad : albumLoad.getImageLoadList()) {
            album.getImages().add(imageService.createImage(imageLoad));
        }
        return album;

    }

    @Override
    public Album createAlbum(AlbumView albumView) throws ImageFailException {
        Album album = new Album(albumView.getName(), albumView.getImageLoadList());
        return album;
    }
}
