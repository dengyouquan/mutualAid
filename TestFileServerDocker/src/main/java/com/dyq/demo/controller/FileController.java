package com.dyq.demo.controller;

import com.dyq.demo.entity.File;
import com.dyq.demo.service.FileService;
import com.dyq.demo.util.MD5Util;
import com.dyq.demo.vo.Response;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    //@Value("127.0.0.1")
    @Value("www.dengyouquan.cn")
    private String address;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/")
    public String index(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC,"uploadDate");
        Pageable pageable = new PageRequest(0, 10, sort);
        model.addAttribute("files", fileService.listFilesByPage(pageable).getContent());
        return "index";
    }

    @GetMapping("files")
    @ResponseBody
    public ResponseEntity<Response> files(@RequestParam(value="pageIndex",required=false,defaultValue="1") int pageIndex,
                                                    @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"uploadDate");
        Pageable pageable = new PageRequest(pageIndex-1, pageSize, sort);
        return ResponseEntity.ok().body(new Response(0,"success",fileService.FileSize(),fileService.listFilesByPage(pageable).getContent()));
    }

    @GetMapping("files/{id}")
    @ResponseBody
    public ResponseEntity<Object> download(@PathVariable String id) throws UnsupportedEncodingException {
        File file = fileService.getFileById(id);
        if (file!=null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.getName().getBytes("utf-8"),"ISO-8859-1"))
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
                    .body(file.getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }


    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<Response> upload(@RequestParam("file") MultipartFile file) {
        File saveFile = null;
        try {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            saveFile = fileService.saveFile(f);
            String path = "http://" + address + ":" + port + "/view/" + saveFile.getId();
            return ResponseEntity.ok().body(new Response(0,"success",0,path));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new Response(0,"success",0,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            fileService.removeFile(id);
            return ResponseEntity.ok().body("DELETE Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity<Object> view(@PathVariable String id) {
        File file = fileService.getFileById(id);
        if (file!=null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getType())
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
                    .body(file.getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }
}
