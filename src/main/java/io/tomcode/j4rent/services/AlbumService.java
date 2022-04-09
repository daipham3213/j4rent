package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.repositories.AlbumRepository;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.AlbumCreate;
import io.tomcode.j4rent.mapper.ImageCreate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("albumService")
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;
    private final IImageService imageService;

    public AlbumService(AlbumRepository albumRepository, IImageService imageService) {
        this.albumRepository = albumRepository;
        this.imageService = imageService;
    }

    @Override
    public Album getAlbumById(UUID id) {
        return albumRepository.getAlbumById(id);
    }

    @Override
    public Album createAlbum(AlbumCreate albumLoad) throws ImageFailException {
        Album album = new Album(albumLoad.getName(), albumLoad.getIsHidden());
        for (ImageCreate image : albumLoad.getImages()) {
            album.getImages().add(imageService.upload(image));
        }
        albumRepository.save(album);
        return album;
    }

}
