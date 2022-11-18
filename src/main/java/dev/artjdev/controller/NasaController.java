package dev.artjdev.controller;

import dev.artjdev.service.NasaService;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class NasaController {
    private final NasaService nasaService;

    public NasaController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @GetMapping(value = "/nasa")
    public ResponseEntity<byte[]> getContent() throws IOException {
        byte[] content = nasaService.getMedia();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(content.length)
                .body(content);
    }
}