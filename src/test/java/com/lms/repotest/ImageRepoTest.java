package com.lms.repotest;


import com.lms.domain.Image;
import com.lms.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ImageRepoTest {
    @Autowired
    private ImageRepository imageRepository;

    MultipartFile file;

    @Test
    public void testFindById() {
        Image image = createImage();
        imageRepository.save(image);
        Image result = imageRepository.findById(image.getId()).get();
        assertEquals(image.getId(), result.getId());
    }

    @Test
    public void testSave() {
        Image image = createImage();
        imageRepository.save(image);
        Image found = imageRepository.findById(image.getId()).get();
        assertEquals(image.getId(), found.getId());
    }
    @Test
    public void testDeleteById() {
        Image image = createImage();
        imageRepository.save(image);
        imageRepository.deleteById(image.getId());
        List<Image> result = new ArrayList<>();
        imageRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }

    private Image createImage(){
        // read the image from the file
        BufferedImage imageFile = null;
        try {
            imageFile = ImageIO.read(new File("src/main/resources/static/book.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create the object of ByteArrayOutputStream class
        ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();

        // write the image into the object of ByteArrayOutputStream class
        try {
            ImageIO.write(imageFile, "png", outStreamObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create the byte array from image
        byte [] img= outStreamObj.toByteArray();

            Image image=new Image("book.png",img);

        return image;
    }
    
}
