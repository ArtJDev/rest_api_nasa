package dev.artjdev.controller;

import dev.artjdev.model.Params;
import dev.artjdev.model.Post;
import dev.artjdev.proxies.NasaProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NasaController {
    @Value("${key.value}")
    private String apiKey;
//    @Value("${media.service.url}")
//    private String mediaUrl;
    private final RestTemplate rest;
    private final NasaProxy nasaProxy;

    public NasaController(RestTemplate rest, NasaProxy nasaProxy) {
        this.rest = rest;
        this.nasaProxy = nasaProxy;
    }

    @GetMapping("/nasa")
    public ResponseEntity<MultipartFile> getContent() {
        Params params = new Params(apiKey);
        Post post = nasaProxy.getPost(params);
        String uri = post.getUrl();
        //        String mediaPath = url.substring(mediaUrl.length());
//        MultipartFile file = nasaProxy.getMedia(mediaPath);

        return rest.getForEntity(uri, MultipartFile.class);
    }
}
