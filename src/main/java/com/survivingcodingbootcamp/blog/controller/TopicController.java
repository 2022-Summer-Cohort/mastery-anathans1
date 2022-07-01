package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepo;
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;

    public TopicController(TopicRepository topicRepo, PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.topicRepo = topicRepo;
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }


    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }
    @PostMapping("/{id}/addPost")
    private String addPost(@PathVariable Long id, @RequestParam String title, @RequestParam String content){
        Topic topic = topicRepo.findById(id).get();
        Post post = new Post(title, topic, content);
        postRepo.save(post);

        return "redirect:/topics/" + id;

    }
}

//    Optional<Hashtag> hashtag1 = hashtagRepo.findByHashtagName(hashtagName);
//        if (!hashtag1.isPresent()){
//                Hashtag hashtag2 = new Hashtag(hashtagName);
//                hashtagRepo.save(hashtag2);
//                post1.addHashtag(hashtag2);
//                }