package dev.artjdev.proxies;

import dev.artjdev.model.Params;
import dev.artjdev.model.Post;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "nasa", url = "${post.service.url}")
public interface NasaProxy {
    @GetMapping
    Post getPost(@SpringQueryMap Params params);

//    @GetMapping(value = "${media.service.url}")
//    MultipartFile getMedia(@Param("mediaPath") String mediaPath);
}