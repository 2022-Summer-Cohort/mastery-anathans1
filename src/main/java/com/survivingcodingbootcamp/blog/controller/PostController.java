package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }


    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }

    @PostMapping("/{id}/addHashtag")
    private String addHashtag(@PathVariable Long id, @RequestParam String hashtagToAdd) {
        if(!hashtagToAdd.startsWith("#")){
            hashtagToAdd = "#" + hashtagToAdd;
        }
        Post post1 = postRepo.findById(id).get();
        Optional<Hashtag> post1Hashtags = hashtagRepo.findByHashtagName(hashtagToAdd);
        if (!post1Hashtags.isPresent()) {
            Hashtag post2Hashtag = new Hashtag(hashtagToAdd);
            hashtagRepo.save(post2Hashtag);
            post1.addHashtag(post2Hashtag);
            postRepo.save(post1);
        }
        else{
            post1.addHashtag(post1Hashtags.get());
            postRepo.save(post1);
        }
        return "redirect:/posts/" + id;
    }
}


