package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Comment;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.CommentRepo;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        comment.setCreateDate(LocalDateTime.now());
        comment.setAuthor(user);
        commentRepo.save(comment);
        updateUserRating(userDetailsRepo.findById(comment.getUser().getId()).get());
        return comment;
    }

    public void deleteComment(Comment comment) {
        commentRepo.delete(comment);
        updateUserRating(userDetailsRepo.findById(comment.getUser().getId()).get());
    }

    public void updateComment(Comment commentFBD, Comment comment){
        commentFBD.setText(comment.getText());
        commentFBD.setRating(comment.getRating());
        commentFBD.setCreateDate(LocalDateTime.now());
        commentRepo.save(commentFBD);
        updateUserRating(userDetailsRepo.findById(commentFBD.getUser().getId()).get());
    }
}
