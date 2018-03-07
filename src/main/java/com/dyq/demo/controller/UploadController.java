package com.dyq.demo.controller;

import com.dyq.demo.domain.DocumentComment;
import com.dyq.demo.domain.User;
import com.dyq.demo.service.DocumentCommentService;
import com.dyq.demo.service.DocumentService;
import com.dyq.demo.service.UserService;
import com.dyq.demo.util.FileUtil;
import com.dyq.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class UploadController {
    @Autowired
    UserService userService;
    @Autowired
    private DocumentCommentService documentCommentService;
    @Autowired
    private DocumentService documentService;

    @GetMapping("/uploadimg")
    public String uploadimg() {
        return "uploadimg";
    }


    @PostMapping("/imageupload")
    //@RequestMapping(value = "/imageupload",method = RequestMethod.POST)
    public String imageupload(){
        System.out.println("imageupload");
        return "redirect:/userspace";
    }

    //处理文件上传
    @PostMapping("/uploadavatar")
    public ResponseEntity<Response> uploadImg(@RequestParam("avatar2") MultipartFile file,
                                              HttpServletRequest request) {

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = request.getSession().getServletContext().getRealPath("/file/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }

        //返回json
        return ResponseEntity.ok().body(new Response(0, "上传头像成功", 0,""));
    }


    @PostMapping("/saveavatar")
    public ResponseEntity<Response> saveavatar(@RequestParam("avatarPath") String avatarPath) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setAvatar(avatarPath);
        System.out.println(user);
        userService.saveUser(user);
        //执行耗时任务
        List<DocumentComment> documentComments = documentCommentService.findByDocumentCommentUser(user);
        System.out.println(documentComments);
        for(DocumentComment dc : documentComments){
            dc.setFromAvatar(user.getAvatar());
            documentCommentService.save(dc);
        }
       /* //通过线程池管理多线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //线程池提交一个异步任务
        System.out.println("====提交异步任务：更新评论头像");
        Future<HashMap<String,String>> future = threadPool.submit(new Callable<HashMap<String,String>>() {
            @Override
            public HashMap<String,String> call() throws Exception {
                System.out.println("异步任务更新评论头像开始执行....");
                //更新评论的头像
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                List<DocumentComment> documentComments = documentCommentService.findByDocumentCommentUser(user);
                System.out.println(documentComments);
                for(DocumentComment dc : documentComments){
                    dc.setFromAvatar(user.getAvatar());
                    documentCommentService.save(dc);
                }
                System.out.println("异步任务更新评论头像执行完毕!");
                //关闭线程池
                if(!threadPool.isShutdown()){
                    threadPool.shutdown();
                }
                return new HashMap<String,String>(){
                    {this.put("futureKey", "成功获取future异步任务结果");}
                };
            }
        });*/

        /*//关闭线程池
        if(!threadPool.isShutdown()){
            threadPool.shutdown();
        }*/

        //返回json
        return ResponseEntity.ok().body(new Response(0, "保存头像成功", 0,""));
    }


    //处理文件上传
    @PostMapping("/uploadfile")
    public ResponseEntity<Response> uploadfile(@RequestParam("file") MultipartFile file,
                                               HttpServletRequest request) {

        String contentType = file.getContentType();
        String fileName = getFileName(file);
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath=request.getSession().getServletContext().getRealPath("/file/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        System.out.println("/file/"+fileName);
        return ResponseEntity.ok().body(new Response(0,"",0,"/file/"+fileName));
    }

    public String getFileName(MultipartFile file){
        long l = System.currentTimeMillis();
        String s = Long.toString(l);
        if(s.length()>7)
            s = s.substring(s.length()-7, s.length());
        else
            s = s+s;
        String fileName = file.getOriginalFilename();
        fileName = fileName +s +"-"+file.getOriginalFilename();
        System.out.println("fileName:"+fileName);
        /*//上传
        file.transferTo(new File(path));
        path = path.split("GameDemo")[1];
        //path = path.substring(1, path.length());
        path = path.replace("\\","/");
        path = path.substring(1, path.length());
        System.out.println("path:"+path);*/
        return fileName;
    }
}
