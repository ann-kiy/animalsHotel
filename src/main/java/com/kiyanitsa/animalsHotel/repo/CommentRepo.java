package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.Comment;
import com.kiyanitsa.animalsHotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
}
