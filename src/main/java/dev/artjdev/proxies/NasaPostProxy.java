package dev.artjdev.proxies;

import dev.artjdev.model.Params;
import dev.artjdev.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "post", url = "${post.service.url}")
public interface NasaPostProxy {
    @GetMapping
    Post getPost(@SpringQueryMap Params params);

}