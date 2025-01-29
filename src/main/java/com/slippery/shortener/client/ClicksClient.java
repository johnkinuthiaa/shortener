package com.slippery.shortener.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "clicks", url = "http://localhost:9000/api/v1/clicks")
public interface ClicksClient {
    @RequestMapping(method = RequestMethod.PUT,value = "/add/{count}/clicks")
    ClickDto addClicks(@PathVariable Long count, @RequestParam Long clickId);

}

