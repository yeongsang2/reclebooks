package com.reclebooks.recle.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface PhotoService {

    // post id 에 대한 image 가져오기
    public List<byte[]> getPhotos(Long postId) throws FileNotFoundException, IOException;
}
