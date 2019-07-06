package com.zpself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zengpeng on 2019/3/28
 */
@SpringBootApplication
@RestController
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @PostMapping("/upload")//上传
    public String upload(){
        return "upload";
    }

    @PostMapping("/download")//下载
    public String download() throws Exception{
        return "download";
    }

}
