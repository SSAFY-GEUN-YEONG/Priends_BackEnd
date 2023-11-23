package com.ssafy.priends.global.component.firebase.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FireBaseService {
    String uploadFiles(MultipartFile file, String nameFile) throws IOException;

}
