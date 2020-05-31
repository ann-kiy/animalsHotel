package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Comment;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.CommentRepo;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo, UserDetailsRepo userDetailsRepo) {
        this.commentRepo = commentRepo;
        this.userDetailsRepo = userDetailsRepo;
    }
    private void updateUserRating(User user){
        List<Comment> comments=commentRepo.findAllByUser(user);
        float ratingUser=0;
        for(Comment i:comments)
            ratingUser+=i.getRating();
        ratingUser/=comments.size();
        user.setRating(ratingUser);
        userDetailsRepo.save(user);
    }
    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        commentRepo.save(comment);
        updateUserRating(comment.getUser());
        return comment;
    }
}
