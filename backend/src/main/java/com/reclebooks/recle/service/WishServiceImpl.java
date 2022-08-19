package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.domain.Wish;
import com.reclebooks.recle.repository.PostRepository;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class WishServiceImpl implements WishService{


    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public Long addWishList(Long postId, Long userId) {

        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();

        Wish wish = new Wish();
        wish.setUser(user);
        wish.setPost(post);

        return wishRepository.save(wish).getId();
    }

    @Override
    public void deleteWishList(Long postId, Long userId) {

    }
}
