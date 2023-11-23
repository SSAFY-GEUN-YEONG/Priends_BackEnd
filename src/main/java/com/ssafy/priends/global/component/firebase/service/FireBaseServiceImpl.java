package com.ssafy.priends.global.component.firebase.service;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class FireBaseServiceImpl implements FireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    @Override
    public String uploadFiles(MultipartFile file, String nameFile) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);

        // 파일 이름 인코딩 할 필요 없음!!! (인코딩 하면 오히려 못불러옴)
        // 파일의 이름을 URL 인코딩 (공백이나 특수문자 처리를 위해)
//        String encodedName = URLEncoder.encode(nameFile, StandardCharsets.UTF_8.name());

        // 파일 업로드
        Blob blob = bucket.create(nameFile, file.getBytes(), file.getContentType());

        // 파일에 대한 공개 읽기 권한 부여
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        // 파일의 공개 URL 반환
        return String.format("https://storage.googleapis.com/%s/%s", firebaseBucket, nameFile);

    }

}
