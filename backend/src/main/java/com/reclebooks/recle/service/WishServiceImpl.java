package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.domain.Wish;
import com.reclebooks.recle.dto.wishdto.WishDto;
import com.reclebooks.recle.repository.PostRepository;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.repository.WishRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class WishServiceImpl implements WishService{


    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public Long addWishList(Long postId, Long userId) throws Exception{

        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();

        for (Wish wish : user.getWishList()) {
            if(wish.getPost().getId().equals(postId)){
                throw new Exception("이미 찜함");
            }
        }

        Wish wish = new Wish();
        wish.setUser(user);
        wish.setPost(post);

        return wishRepository.save(wish).getId();
    }

    @Override
    public void deleteWishList(Long postId, Long userId) throws Exception {
         Wish wish = wishRepository.findByPostIdAndUserId(postId, userId).orElse(null);

         // 본인인지 체크
         if (wish.getUser().getId() != userId) {
             throw new Exception("찜하지 않았음");
         }
        wishRepository.deleteById(wish.getId());
    }

    @Override
    public List<WishDto> getWishList(Long userId) {


        List<Wish> wishList = wishRepository.findAllByUserId(userId);

        List<WishDto> wishDtoList = new ArrayList<>();

        if(!wishList.isEmpty()){
            for (Wish wish : wishList) {
                WishDto wishDto = WishDto.builder()
                        .postId(wish.getPost().getId())
                        .title(wish.getPost().getTitle())
                        .price(wish.getPost().getPrice())
                        .isSell(wish.getPost().isSell())
                        .build();
                wishDtoList.add(wishDto);
            }
        }
        return wishDtoList;
    }
}
