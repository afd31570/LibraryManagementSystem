package com.lms.service;

import com.lms.domain.Image;
import com.lms.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public String saveImage(MultipartFile file){
        String fileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Image image=null;
        byte[] img;

        try {
            img=file.getBytes();
            image=new Image(fileName,img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageRepository.save(image);
        return image.getId();
    }
}
