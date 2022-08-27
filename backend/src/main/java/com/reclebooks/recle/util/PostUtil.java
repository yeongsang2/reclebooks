//package com.reclebooks.recle.util;
//
//
//import com.reclebooks.recle.domain.Photo;
//import com.reclebooks.recle.domain.Post;
//import com.reclebooks.recle.dto.postdto.GetPostDto;
//import org.apache.commons.io.IOUtils;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class PostUtil {
//
//    public static List<GetPostDto> makeGetPostDtoWithPhoto(List<Post> postList) throws IOException {
//
//        List<GetPostDto> postDtos = new ArrayList<>();
//
//        for (Post post : postList) {
//            List<Photo> photoList = post.getPhotoList();
//            if (!photoList.isEmpty()) {  // 사진이 있으면
//
//                String path = photoList.get(0).getPhotoPath(); //이미지 하나 불러오기
//
//                String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
//                InputStream imageStream = new FileInputStream(absolutePath + path);
//                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//                imageStream.close();
//
//                postDtos.add(GetPostDto.from(post, imageByteArray));
//            } else {
//                postDtos.add(GetPostDto.from(post, null));
//            }
//        }
//
//        return postDtos;
//    }
//
//}

