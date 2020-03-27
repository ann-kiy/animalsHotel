package com.kiyanitsa.animalsHotel.repo;

import com.kiyanitsa.animalsHotel.domain.Message;
import com.kiyanitsa.animalsHotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Long> {
    List<Message> findAllByAuthor(User user);
}
