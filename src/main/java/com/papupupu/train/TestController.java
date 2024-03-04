package com.papupupu.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("hellow")
    public String heollow(){
        return "hellowcascas";
    }
}
