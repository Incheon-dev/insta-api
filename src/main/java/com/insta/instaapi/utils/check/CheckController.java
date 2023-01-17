package com.insta.instaapi.utils.check;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @RequestMapping(value = "/api/v1/check", method = RequestMethod.GET)
    public String check() {
        return "check";
    }
}
