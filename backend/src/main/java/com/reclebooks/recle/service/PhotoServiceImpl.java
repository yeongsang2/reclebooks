package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Photo;
import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.repository.PhotoRepository;
import com.reclebooks.recle.repository.PostRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private final PostRepository postRepository;
    @Override
    public List<byte[]> getPhotos(Long postId) throws IOException {
        System.out.println("--------------");

        Post post = postRepository.findById(postId).get();
        List<Photo> photoList = post.getPhotoList();

        System.out.println("--------------");
        List<byte[]> bytePhotos = new ArrayList<>();
        //변환
        if(!photoList.isEmpty()) { //photos not empty면
            for (Photo photo : photoList) {

                String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
                String path = photo.getPhotoPath();

                InputStream imageStream = new FileInputStream(absolutePath + path);
                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
                bytePhotos.add(imageByteArray);
                imageStream.close();
            }
        }
        return bytePhotos;
    }
}
