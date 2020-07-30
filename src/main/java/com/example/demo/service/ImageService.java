package com.example.demo.service;

import com.example.demo.models.Images;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imgRepo;

    public ImageService() {
    }

    void initiateImages(){
        List<Images> all = imgRepo.findAll();
        if(all.size()==0){
            for (int i = 1 ; i < 20; i++){
                Images img = new Images();
                img.setNumber(i);
                 imgRepo.save(img);
            }
        }
    }

    List<Images> getAllImages(){
        List<Images> all = imgRepo.findAll();
        if(all.size()==0){
            initiateImages();
            all = imgRepo.findAll();
        }
        return all;
    }
}
