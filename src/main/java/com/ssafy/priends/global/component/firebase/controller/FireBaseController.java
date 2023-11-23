package com.ssafy.priends.global.component.firebase.controller;

import com.ssafy.priends.global.common.dto.Message;
import com.ssafy.priends.global.component.firebase.service.FireBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FireBaseController {
    private final FireBaseService fireBaseService;

    @PostMapping("/upload")
    public ResponseEntity<Message<String>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("nameFile") String nameFile) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.ok().body(Message.fail("0", "is empty"));
        }
        String url = fireBaseService.uploadFiles(file, nameFile);
        return ResponseEntity.ok().body(Message.success(url));
    }

}
