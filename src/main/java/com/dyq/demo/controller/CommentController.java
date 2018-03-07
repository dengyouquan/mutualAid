package com.dyq.demo.controller;

import com.dyq.demo.domain.Document;
import com.dyq.demo.domain.DocumentComment;
import com.dyq.demo.domain.DocumentReply;
import com.dyq.demo.domain.User;
import com.dyq.demo.service.DocumentCommentService;
import com.dyq.demo.service.DocumentReplyService;
import com.dyq.demo.service.DocumentService;
import com.dyq.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private DocumentCommentService documentCommentService;
    @Autowired
    private DocumentReplyService documentReplyService;
    @Autowired
    private DocumentService documentService;

    @GetMapping("/comment")
    public String comment(Model model,
                          @RequestParam(value="async",required=false) boolean async,
                          @RequestParam(value="page",required=false,defaultValue="1") int pageIndex,
                          @RequestParam(value="limit",required=false,defaultValue="5") int pageSize,
                          @RequestParam(value="d_id",required=false,defaultValue = "0") Long d_id,
                          @ModelAttribute("d_id") Long red_id) {
        Long id = d_id==0?red_id:d_id;
        System.out.println("d_id:"+d_id+",red_id:"+red_id+",id:"+id);
        List<DocumentComment> documentComments = null;
        Page<DocumentComment> documentCommentPage = null;
        Long commentCount = 0L;
        Sort newSort = new Sort(Sort.Direction.DESC,"createdAt");
        Pageable pageable = new PageRequest(pageIndex-1, pageSize,newSort);
        Document document = documentService.findById(id);
        //documentComments = document.getComments();无法用分页
        //commentCount = documentComments.size();
        documentCommentPage = documentCommentService.findByCommentDocument(document,pageable);
        documentComments = documentCommentPage.getContent();
        commentCount = documentCommentPage.getTotalElements();
        model.addAttribute("documentComments",documentComments);
        System.out.println("documentComments:"+documentComments);
        model.addAttribute("commentCount",commentCount);
        System.out.println("commentCount:"+commentCount);
        //System.out.println(async?"redirect:/documentdetail?did="+id+" :: #commentContainerSysc":"redirect:/documentdetail?did="+id);
        System.out.println(async?"/fragments/comment::#commentContainerSysc":"/fragments/comment");
        //return (async?"redirect:/documentdetail?did="+id+"::#commentContainerSysc":"redirect:/documentdetail?did="+id);
        return (async?"/fragments/comment::#commentContainerSysc":"/fragments/comment");

    }

    @PostMapping("/document/addComment")
    public ResponseEntity<Response> addComment(Model model, HttpServletRequest request) {
        DocumentComment documentComment = new DocumentComment();
        String content = request.getParameter("content");
        Long d_id = Long.valueOf(request.getParameter("d_id"));
        Document document = documentService.findById(d_id);
        System.out.println("content:"+content+",d_id"+d_id+",document:"+document);
        documentComment.setContent(content);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        documentComment.setFromAvatar(user.getAvatar());
        documentComment.setFromName(user.getName());
        documentComment.setDocumentCommentUser(user);
        documentComment.setCommentDocument(document);
        System.out.println(documentComment.toString());
        documentCommentService.save(documentComment);
        //增加评论量
        document.setCommentSize(document.getCommentSize()+1);
        documentService.save(document);
        return ResponseEntity.ok().body(new Response(0,"ok",0,d_id));
    }


    @PostMapping("/document/addReply")
    public ResponseEntity<Response> addReply(Model model, HttpServletRequest request) {
        DocumentReply documentReply = new DocumentReply();
        String content = request.getParameter("content");
        Long comment_id = Long.valueOf(request.getParameter("comment_id"));
        DocumentComment documentComment = documentCommentService.findById(comment_id);
        User toUser = documentComment.getDocumentCommentUser();
        System.out.println("content:"+content+",comment_id"+comment_id+",documentComment:"+documentComment+",toUser:"+toUser);
        documentReply.setContent(content);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        documentReply.setFromAvatar(user.getAvatar());
        documentReply.setFromName(user.getName());
        documentReply.setDocumentReplyUser(user);
        documentReply.setToDocumentReplyUser(toUser);
        documentReply.setDocumentComment(documentComment);
        System.out.println("====================================");
        System.out.println("documentReply:"+documentReply.toString());
        documentReplyService.save(documentReply);
        return ResponseEntity.ok().body(new Response(0,"ok",0,comment_id));
    }

}
