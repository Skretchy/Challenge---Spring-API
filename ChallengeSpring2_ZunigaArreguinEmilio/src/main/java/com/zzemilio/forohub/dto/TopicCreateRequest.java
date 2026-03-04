package com.zzemilio.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicCreateRequest(
        @NotBlank @Size(max=200) String title,
        @NotBlank String message,
        @NotBlank @Size(max=120) String course
) {}
