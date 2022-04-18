package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Album;
import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.repositories.AlbumRepository;
import io.tomcode.j4rent.core.services.IAlbumService;
import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
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
        return albumRepository.findAlbumById(id);
    }

    @Override
    public Album createAlbum(AlbumCreate albumLoad) throws ImageFailException {
        Album album = new Album(albumLoad.getName(), albumLoad.isHidden());
        for (ImageCreate image : albumLoad.getImages()) {
            album.getImages().add(imageService.upload(image));
        }
        return albumRepository.save(album);
    }

    @Override
    public Album createAlbum(AlbumView albumCreate) {
        Album album = new Album();
        List<Image> imageList = new ArrayList<>();
        for (ImageView image : albumCreate.getImages()) {
            imageList.add(imageService.create(image));
        }
        album.setImages(imageList);
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(AlbumUpdate albumUpdate) throws ImageFailException {
        Album album = albumUpdate.getAlbum();
        for (ImageCreate image : albumUpdate.getAlbumCreate().getImages()) {
            album.getImages().add(imageService.upload(image));
        }
        albumRepository.save(album);
        return album;
    }

}
