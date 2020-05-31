package com.kiyanitsa.animalsHotel.controller;

import com.kiyanitsa.animalsHotel.domain.Comment;
import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.dto.EventType;
import com.kiyanitsa.animalsHotel.services.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public Comment create(@RequestBody Comment comment, @AuthenticationPrincipal User user){
        return commentService.create(comment, user);
    }
}
