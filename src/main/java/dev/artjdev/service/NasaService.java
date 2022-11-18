package dev.artjdev.service;

import dev.artjdev.model.Params;
import dev.artjdev.model.Post;
import dev.artjdev.proxies.NasaMediaProxy;
import dev.artjdev.proxies.NasaPostProxy;
import feign.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NasaService {
    @Value("${key.value}")
    private String apiKey;
    @Value("${media.service.url}")
    private String mediaUrl;
    private final NasaPostProxy nasaPostProxy;
    private final NasaMediaProxy nasaMediaProxy;

    public NasaService(NasaPostProxy nasaPostProxy, NasaMediaProxy nasaMediaProxy) {
        this.nasaPostProxy = nasaPostProxy;
        this.nasaMediaProxy = nasaMediaProxy;
    }

    public byte[] getMedia() throws IOException {
        Params params = new Params(apiKey);
        Post post = nasaPostProxy.getPost(params);
        String uri = post.getUrl();
        String mediaPath = uri.substring(mediaUrl.length());
        Response response = nasaMediaProxy.getMedia(mediaPath);
        return response.body().asInputStream().readAllBytes();
    }
}