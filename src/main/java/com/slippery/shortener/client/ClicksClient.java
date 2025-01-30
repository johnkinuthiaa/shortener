package com.slippery.shortener.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "clicks", url = "http://localhost:9000/api/v1/clicks")
public interface ClicksClient {
    @RequestMapping(method = RequestMethod.PUT,value = "/add/{count}/clicks")
    ClickDto addClicks(@PathVariable Long count, @RequestParam Long clickId);

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    ClickDto createClickRecord(@RequestBody Object clicks);

}

