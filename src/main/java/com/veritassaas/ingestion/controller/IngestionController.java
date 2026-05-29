package com.veritassaas.ingestion.controller;

import com.veritassaas.ingestion.dto.EventPayload;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
public class IngestionController {

    private static final Logger log = LoggerFactory.getLogger(IngestionController.class);

    @PostMapping
    public ResponseEntity<Map<String, Object>> ingestEvent(@Valid @RequestBody EventPayload payload) {
        // Log the incoming request details for traceability
        log.info("Ingress endpoint hit: EventID={}, TenantID={}, Type={}",
                payload.eventId(), payload.tenantId(), payload.eventType());

        // Prepare a structured JSON success response
        Map<String, Object> response = Map.of(
                "status", "ACCEPTED",
                "eventId", payload.eventId(),
                "receivedAt", Instant.now().toString()
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
