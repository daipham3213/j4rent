package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.repositories.ImageRepository;
import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageLoad;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("imageService")
public class ImageService implements IImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageService(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public void upload(ImageLoad imageLoad) throws ImageFailException {
        try {
            Map uploadResult = cloudinaryService.upload(imageLoad.getFile());
            Image image = new Image(imageLoad.getTitle(), (String) uploadResult.get("url"));
            imageRepository.save(image);
        } catch (Exception e) {
            throw new ImageFailException();
        }

    }

    public Image createImage(ImageLoad imageLoad) throws ImageFailException {
        try {
            Map uploadResult = cloudinaryService.upload(imageLoad.getFile());
            Image image = new Image(imageLoad.getTitle(), (String) uploadResult.get("url"));
            imageRepository.save(image);
            return image;
        } catch (Exception e) {
            throw new ImageFailException();
        }

    }

}
