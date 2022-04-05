package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.repositories.ImageRepository;
import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageCreate;
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
    public void upImage(ImageCreate file) throws ImageFailException {
        try {
            Map uploadResult = cloudinaryService.upload(file.getFile());
            Image image = new Image(file.getTitle(), (String) uploadResult.get("url"));
            imageRepository.save(image);
        } catch (Exception e) {
            throw new ImageFailException();
        }

    }

    public Image createImage(ImageCreate imageUp) throws ImageFailException {
        try {
            Map uploadResult = cloudinaryService.upload(imageUp.getFile());
            Image image = new Image(imageUp.getTitle(), (String) uploadResult.get("url"));
            imageRepository.save(image);
            return image;
        } catch (Exception e) {
            throw new ImageFailException();
        }

    }

}
