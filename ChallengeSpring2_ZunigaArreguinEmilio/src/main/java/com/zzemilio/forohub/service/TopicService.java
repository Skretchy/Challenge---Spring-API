package com.zzemilio.forohub.service;

import com.zzemilio.forohub.domain.Topic;
import com.zzemilio.forohub.dto.*;
import com.zzemilio.forohub.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public List<TopicResponse> listAll() {
        return topicRepository.findAll().stream().map(this::toResponse).toList();
    }

    public TopicResponse getById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return toResponse(topic);
    }

    public TopicResponse create(TopicCreateRequest req) {
        if (topicRepository.existsByTitleAndMessage(req.title(), req.message())) {
            throw new RuntimeException("Tópico duplicado (mismo título y mensaje).");
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var author = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Topic topic = new Topic(req.title(), req.message(), req.course(), author);
        return toResponse(topicRepository.save(topic));
    }

    public TopicResponse update(Long id, TopicUpdateRequest req) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        topic.update(req.title(), req.message(), req.course());
        return toResponse(topicRepository.save(topic));
    }

    public void delete(Long id) {
        if (!topicRepository.existsById(id)) throw new RuntimeException("Tópico no encontrado");
        topicRepository.deleteById(id);
    }

    private TopicResponse toResponse(Topic t) {
        return new TopicResponse(
                t.getId(),
                t.getTitle(),
                t.getMessage(),
                t.getCourse(),
                t.getAuthor().getEmail(),
                t.getCreatedAt()
        );
    }
}
