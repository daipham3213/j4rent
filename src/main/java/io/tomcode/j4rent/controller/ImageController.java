package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.services.IImageService;
import io.tomcode.j4rent.exception.ImageFailException;
import io.tomcode.j4rent.mapper.ImageLoad;
import io.tomcode.j4rent.mapper.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
@Controller
@RequestMapping("/image")
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

//    @GetMapping("")
//    public String create(@ModelAttribute ImageLoad imageLoad, Model model) {
//        return "image_test";
//    }

//
//    @PostMapping("/create")
//    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
//        modelMap.addAttribute("file", file);
//        try {
//            BufferedImage bi = ImageIO.read(file.getInputStream());
//            if (bi == null) {
//                throw new ImageFailException();
//            }
//            ImageLoad imageLoad = new ImageLoad("Aaa", file);
//            iImageService.upImage(imageLoad);
//            return  "image_test";
////            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "sent img"), HttpStatus.OK);
//        } catch (Exception e) {
//           return  null;
//        }
//
//    }


    @PostMapping("/create")
    public ResponseEntity<Object> upload(@RequestBody ImageLoad imageLoad) throws ImageFailException {
        try {
            BufferedImage bi = ImageIO.read(imageLoad.getFile().getInputStream());
            if (bi == null) {
                throw new ImageFailException();
            }
            imageService.upImage(imageLoad);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "sent img"), HttpStatus.OK);
        } catch (Exception e) {
            throw new ImageFailException();
        }
    }

}
