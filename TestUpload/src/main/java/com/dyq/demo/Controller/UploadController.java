package com.dyq.demo.Controller;


import com.dyq.demo.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadController {
    @GetMapping("/uploadimg")
    public String uploadimg() {
        return "uploadimg";
    }

    @GetMapping("/qiantu")
    public String qiantu() {
        return "qiantu";
    }

    //@PostMapping("/imageupload")
    @RequestMapping(value = "/imageupload",method = RequestMethod.POST)
    public String imageupload(){
        System.out.println("imageupload");
        return "qiantu";
    }

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "ok";
    }
}
