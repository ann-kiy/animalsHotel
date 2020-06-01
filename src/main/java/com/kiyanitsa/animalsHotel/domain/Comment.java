package com.kiyanitsa.animalsHotel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.kiyanitsa.animalsHotel.views.ViewMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(of={"id"})
public class Comment {
    @Id
    @GeneratedValue
//    @JsonView(ViewMessage.IdName.class)
    private Long id;

//    @JsonView(ViewMessage.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
//    @JsonView(ViewMessage.FullMessage.class)
    private User author;

//    @JsonView(ViewMessage.FullMessage.class)
    private float rating;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}
