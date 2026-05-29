package com.veritassaas.ingestion.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;

public record EventPayload(
        @NotBlank(message = "Event ID is required for tracking")
        String eventId,

        @NotBlank(message = "Tenant ID is required for multi-tenant isolation")
        String tenantId,

        @NotBlank(message = "Event type must be defined (e.g., 'API_CALL')")
        String eventType,

        @NotNull(message = "Timestamp must be provided")
        Instant timestamp,

        // Metadata map allows flexibility for extra payload details
        Map<String, Object> metadata
) {}
