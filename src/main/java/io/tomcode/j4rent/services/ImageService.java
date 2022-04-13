package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.repositories.ImageRepository;
import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageCreate;
import io.tomcode.j4rent.mapper.ImageView;
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
    public Image upload(ImageCreate file) throws ImageFailException {
        try {
            Map<String, Object> uploadResult = cloudinaryService.upload(file.getFile());
            Image image = new Image(file.getTitle(), (String) uploadResult.get("url"));
            return imageRepository.save(image);
        } catch (Exception e) {
            throw new ImageFailException();
        }

    }



}
