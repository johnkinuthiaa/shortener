package com.slippery.shortener.service.impl;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.models.Clicks;
import com.slippery.shortener.models.UrlModel;
import com.slippery.shortener.models.Users;
import com.slippery.shortener.repository.ClicksRepository;
import com.slippery.shortener.repository.UrlRepository;
import com.slippery.shortener.repository.UserRepository;
import com.slippery.shortener.service.UrlService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImplementation implements UrlService {
    private final UrlRepository repository;
    private final UserRepository userRepository;
    private final ClicksRepository clicksRepository;

    public UrlServiceImplementation(UrlRepository repository, UserRepository userRepository, ClicksRepository clicksRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.clicksRepository = clicksRepository;
    }
    private String createShortUrl(){
        Random random =new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shrtn.");

        String[] chars ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","z","y","z"};
        for(int i= 0;i<12;i++){
            stringBuilder.append(chars[random.nextInt(20)]);
        }
        return stringBuilder.toString();

    }

    @Override
    public UrlDto shorten(UrlModel urlModel,Long userId) {
        UrlDto response =new UrlDto();
        Optional<Users> existingUser =userRepository.findById(userId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        Clicks clicks =new Clicks();
        clicks.setClicks(0L);

        var shortUrl =createShortUrl();
        urlModel.setShortUrl(shortUrl);
        urlModel.setClicks(clicks);
        urlModel.setUsers(existingUser.get());
        clicks.setUrl(urlModel);
        clicksRepository.save(clicks);
        repository.save(urlModel);
        var urls =existingUser.get().getUrlForUser();
        urls.add(urlModel);
        existingUser.get().setUrlForUser(urls);
        userRepository.save(existingUser.get());
        response.setMessage("Url created");
        response.setStatusCode(200);
        response.setUrlModel(urlModel);
        return response;
    }

    @Override
    public UrlDto findAll() {
        UrlDto response =new UrlDto();
        response.setUrlModels(repository.findAll());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UrlDto findAllByUser(Long userId) {
        UrlDto response =new UrlDto();
        Optional<Users> existingUsers =userRepository.findById(userId);
        if(existingUsers.isEmpty()){
            response.setMessage("user not found");
            response.setStatusCode(404);
            return response;
        }
        response.setUrlModels(existingUsers.get().getUrlForUser());
        response.setMessage("All links for" +existingUsers.get().getUsername());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UrlDto deleteById(Long urlId) {
        UrlDto response =new UrlDto();
        Optional<UrlModel> existingUrl =repository.findById(urlId);
        if(existingUrl.isEmpty()){
            response.setStatusCode(404);
            response.setMessage("Url not found");
            return response;
        }

        return response;
    }

    @Override
    public UrlDto getOriginal(String shortenedUrl) {
        UrlDto response =new UrlDto();

        List<UrlModel> existingLink =repository.findAll().stream()
                .filter(urlModel -> urlModel.getShortUrl().equalsIgnoreCase(shortenedUrl))
                .toList();


        if(existingLink.isEmpty()){
            response.setMessage("No link found");
            response.setStatusCode(404);
            return response;
        }
        Long totalCLicks =existingLink.get(0).getClicks().getClicks();
        totalCLicks =totalCLicks+1;
        existingLink.get(0).getClicks().setClicks(totalCLicks);
        repository.save(existingLink.get(0));

        response.setOriginalUrl(existingLink.get(0).getOriginalUrl());
        response.setMessage("original url");
        response.setStatusCode(200);
        return response;
    }
}
