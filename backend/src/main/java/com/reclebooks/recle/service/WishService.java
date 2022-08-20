package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Wish;
import com.reclebooks.recle.dto.wishdto.WishDto;

import javax.swing.text.html.Option;
import java.util.List;

public interface WishService {

    //찜하기
    public Long addWishList(Long postId, Long userId) throws Exception;

    //찜해제
    public void deleteWishList(Long postId, Long userId) throws Exception;

    //찜 목록 불러오기
    public List<WishDto> getWishList(Long userId);
}
