package com.kiyanitsa.animalsHotel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.views.ViewMessage;
import com.kiyanitsa.animalsHotel.views.ViewUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @JsonView(ViewUser.Id.class)
    private Long id;

    private String idWeb;

    @JsonView(ViewUser.IdName.class)
    private String name;

    private String img;
    private String email;
    private String phone;
    private String locale;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private boolean active;
    private String activationCode;
  //  @ElementCollection(fetch = FetchType.EAGER)

//    private Set<Long> id_animals;

    //    @ElementCollection(fetch = FetchType.LAZY)
    //private favorites

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
    public String getImg(){
        if(img==null)
            return "no_foto.png";
        else
            return img;
    }
}
