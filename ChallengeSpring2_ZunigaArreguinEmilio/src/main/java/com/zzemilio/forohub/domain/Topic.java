package com.zzemilio.forohub.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "topics",
        uniqueConstraints = @UniqueConstraint(name="uk_topic_title_message", columnNames = {"title","message"})
)
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, length = 120)
    private String course;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    protected Topic() {}

    public Topic(String title, String message, String course, User author) {
        this.title = title;
        this.message = message;
        this.course = course;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getCourse() { return course; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getAuthor() { return author; }

    public void update(String title, String message, String course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }
}