package com.spring.bittlebittle.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
public class ImageController {

	Logger log = LogManager.getLogger("case3");
	
    @GetMapping("image")
    public void getImage( @RequestParam("path") String path,
                        @RequestParam("name") String imageName,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        String IMAGE_DIRECTORY = request.getServletContext().getResource("/resources/static/image/"+path+"/").getPath();
        // IMAGE_DIRECTORY 상수는 이미지가 저장된 디렉토리의 경로
        
        File imageFile = new File(IMAGE_DIRECTORY + File.separator + imageName);
        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = URLConnection.guessContentTypeFromName(imageName);
        response.setContentType(contentType);

        try (OutputStream out = response.getOutputStream();
             InputStream in = new FileInputStream(imageFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
