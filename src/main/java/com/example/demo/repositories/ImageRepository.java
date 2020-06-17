package com.example.demo.repositories;

import com.example.demo.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Integer> {
}
