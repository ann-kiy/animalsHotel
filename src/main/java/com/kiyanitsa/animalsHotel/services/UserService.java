package com.kiyanitsa.animalsHotel.services;

import com.kiyanitsa.animalsHotel.domain.Role;
import com.kiyanitsa.animalsHotel.domain.User;
import com.kiyanitsa.animalsHotel.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsRepo userRepository;
    @Autowired
    private MailService mailService;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return  user;
    }
    public boolean saveUser(User user){
        User userFromDB=userRepository.findByEmail(user.getEmail());
        if(userFromDB!=null){
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setLastVisit(LocalDateTime.now());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRating((float) 0.0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

       sendMessage(user);
        return true;
    }
    public void updateUser(User user, User userFBD){
        boolean isEmailChange=(!user.getEmail().equals(userFBD.getEmail()) && !user.getEmail().equals(""));
        userFBD.setName(user.getName());
        userFBD.setLocale(user.getLocale());
        userFBD.setPhone(user.getPhone());

        if(isEmailChange){
            userFBD.setEmail(user.getEmail());
            userFBD.setActivationCode(UUID.randomUUID().toString());
            sendMessage(userFBD);
        }
        userRepository.save(userFBD);
    }

    private void sendMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())){
            String message=String.format(
                    "Hello, %s\n"+
                            "Welcome to HotelAnimals. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailService.sent(user.getEmail(),"Activation code",message);
        }
    }

    public User addImg(User user, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        if(!file.getOriginalFilename().equals("")) {
            File uploadDir= new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultFileName = UUID.randomUUID().toString()+"."+file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            user.setImg(resultFileName);
        }
        return user;
    }
    public User findByEmail(String email){
        if(email.isEmpty())
            return null;
        User user =userRepository.findByIdWeb(email).get();
        return user;

    }
    public Optional<User> findByIdWeb(String id){
        if(id.isEmpty())
            return null;
        return userRepository.findByIdWeb(id);
    }

    public User findByCode(String code){
        return userRepository.findByActivationCode(code);
    }

    public boolean activateUser(String code) {
        User user=userRepository.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
    public boolean isFullDataUser(User user){
        return !user.getEmail().isEmpty() && !user.getName().isEmpty() &&
                !user.getPhone().isEmpty();
    }
}
