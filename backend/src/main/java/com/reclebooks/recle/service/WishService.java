package com.reclebooks.recle.service;

public interface WishService {

    //찜하기
    public Long addWishList(Long postId, Long userId) throws Exception;

    //찜해제
    public void deleteWishList(Long postId, Long userId) throws Exception;

}
