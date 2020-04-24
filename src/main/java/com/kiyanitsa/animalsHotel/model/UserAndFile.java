package com.kiyanitsa.animalsHotel.model;

import com.kiyanitsa.animalsHotel.domain.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserAndFile {
    User user;
    MultipartFile file;
}
