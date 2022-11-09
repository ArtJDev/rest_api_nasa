package dev.artjdev.proxies;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "media", url = "${media.service.url}")
public interface NasaMediaProxy {

    @GetMapping("{mediaPath}")
    Response getMedia(@PathVariable("mediaPath") String mediaPath);

}