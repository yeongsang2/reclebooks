package com.reclebooks.recle.service;

import com.reclebooks.recle.dto.bookdto.SearchBookDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    private final String clientId = "d5Efx46Mjzm0TT8rw_Ph";
    private final String clientSecret = "JU5XYCocBk";

    private String apiURL = "https://openapi.naver.com/v1/search/book.json?display=20";
    @Override
    public SearchBookDto searchBook(String keyword) {


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity();
        URI targetUrl = UriComponentsBuilder
                .fromUriString(apiURL)
                .queryParam("query", keyword)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, SearchBookDto.class).getBody();
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", clientId);
        httpHeaders.set("X-Naver-Client-Secret", clientSecret);
        return new HttpEntity<>(httpHeaders);
    }
}
