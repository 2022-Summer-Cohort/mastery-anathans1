package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;

@Controller
public class HashtagController {
    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }
    @RequestMapping ("/hashtags")
    public String showAllHashtags(Model model){
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtag-template";
    }
    @RequestMapping("/hashtag/{hashtag}")
    public String showOneHashtag(Model model, @PathVariable String hashtag){
        model.addAttribute("hashtag",hashtagRepo.findByHashtag(hashtag));
        return "single-hashtag-template";
    }

}
