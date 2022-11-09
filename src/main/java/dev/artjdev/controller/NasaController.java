package dev.artjdev.controller;

import dev.artjdev.model.Params;
import dev.artjdev.model.Post;
import dev.artjdev.proxies.NasaMediaProxy;
import dev.artjdev.proxies.NasaPostProxy;
import feign.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Controller
public class NasaController {
    @Value("${key.value}")
    private String apiKey;
    @Value("${media.service.url}")
    private String mediaUrl;
    private final String contentType = "Content-Type";
    private final NasaPostProxy nasaPostProxy;
    private final NasaMediaProxy nasaMediaProxy;

    public NasaController(NasaPostProxy nasaProxy, NasaMediaProxy nasaMediaProxy) {
        this.nasaPostProxy = nasaProxy;
        this.nasaMediaProxy = nasaMediaProxy;
    }

    @GetMapping(value = "/nasa")
    public ResponseEntity<byte[]> getContent() throws IOException {
        Params params = new Params(apiKey);
        Post post = nasaPostProxy.getPost(params);
        String uri = post.getUrl();
        String mediaPath = uri.substring(mediaUrl.length());
        Response response = nasaMediaProxy.getMedia(mediaPath);
        Map<String, Collection<String>> headers = response.headers();
        String type = headers.get(contentType).toString().substring(1, headers.get(contentType).toString().length() - 1);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(type))
                .contentLength(response.body().length())
                .body(response.body().asInputStream().readAllBytes());
    }
}