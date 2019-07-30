package com.springreact.chating.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Message {

    private @Id @GeneratedValue Long id;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private String message;
    private Long sentAt;

    public Message(User from, User to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.sentAt = new Date().getTime();
    }
}
