package com.spring.bittlebittle.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImageUploadUtil {

    private static final String UPLOAD_DIRECTORY = "uploaded_images";

    private Logger log = LogManager.getLogger("case3");

    // 일반 메소드

    // * 스프링에서 반드시 요청을 처리하는 메소드만 Controller 에 들어가라는 법은 없다!!
    // 현재 넘어온 첨부파일의 이름을 수정 후 서버의 그 폴더에 저장하는 메소드
    // => url 요청을 처리하는 메소드가 아닌 일반 메소드로 만듬
    public String saveFile(MultipartFile upfile, HttpServletRequest request, String path) throws MalformedURLException {

        // 파일명 수정 후 업로드 시키기
        // 예) "flower.png" => "2022061511020112345.png"
        // 1. 원본파일명 가져오기
        String originName = upfile.getOriginalFilename(); // "flower.png"

        // 2. 시간 형식을 문자열로 뽑아내기
        // "20220615110201" (년월일시분초) => SimpleDateFormat
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 3. 뒤에 붙을 5자리 랜덤값 뽑기
        // "13253"
        int ranNum = (int)(Math.random() * 90000 + 10000);

        // 4. 원본파일명으로부터 확장자 부분만 추출
        // ".png"
        String ext = originName.substring(originName.lastIndexOf("."));

        // 5. 모두 이어 붙이기
        String changeName = currentTime + ranNum + ext;

        // 6. 업로드 하고자 하는 물리적인 경로 알아내기
        String savePath = request.getServletContext().getRealPath("/resources/static/image/"+path);
//      String savePath = request.getServletContext().getResource("/resources/static/image/"+path).getPath();

        // 7. 경로와 수정파일명을 합체시킨 후 저장
        try {
            File destinationFile = new File(savePath + File.separator + changeName);
            log.debug(destinationFile);
            FileUtils.writeByteArrayToFile(destinationFile, upfile.getBytes());
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return changeName;
    }

}
