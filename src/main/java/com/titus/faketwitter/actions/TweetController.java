package com.titus.faketwitter.actions;

import com.titus.faketwitter.tweets.Tweet;
import com.titus.faketwitter.tweets.TweetService;
import com.titus.faketwitter.users.User;
import com.titus.faketwitter.users.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TweetController {

  private final UserService userService;
  private final TweetService tweetService;

  @Autowired
  public TweetController(UserService userService, TweetService tweetService) {
    this.userService = userService;
    this.tweetService = tweetService;
  }
  
  @GetMapping(value= {"/tweets", "/"})
  public String getFeed(Model model){
      List<Tweet> tweets = tweetService.findAll();
      model.addAttribute("tweetList", tweets);
      return "feed";
  }
  
  @GetMapping(value = "/tweets/new")
  public String getTweetForm(Model model) {
      model.addAttribute("tweet", new Tweet());
      return "newTweet";
  }
  
  @PostMapping(value = "/tweets")
  public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model) {
      User user = userService.getLoggedInUser();
      if (!bindingResult.hasErrors()) {
          tweet.setUser(user);
          tweetService.save(tweet);
          model.addAttribute("successMessage", "Tweet successfully created!");
          model.addAttribute("tweet", new Tweet());
      }
      return "newTweet";
  }
}