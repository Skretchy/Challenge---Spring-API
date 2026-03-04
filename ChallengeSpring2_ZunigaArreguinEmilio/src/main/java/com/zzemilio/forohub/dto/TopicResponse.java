package com.zzemilio.forohub.dto;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String course,
        String authorEmail,
        LocalDateTime createdAt
) {}
