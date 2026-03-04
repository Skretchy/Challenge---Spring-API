package com.zzemilio.forohub.controller;

import com.zzemilio.forohub.dto.*;
import com.zzemilio.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<TopicResponse>> list() {
        return ResponseEntity.ok(topicService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(@RequestBody @Valid TopicCreateRequest req) {
        TopicResponse created = topicService.create(req);
        return ResponseEntity.created(URI.create("/topicos/" + created.id())).body(created); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateRequest req) {
        return ResponseEntity.ok(topicService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}