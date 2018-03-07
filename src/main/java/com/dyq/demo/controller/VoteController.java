package com.dyq.demo.controller;

import com.dyq.demo.domain.*;
import com.dyq.demo.service.DocumentCommentService;
import com.dyq.demo.service.DocumentReplyService;
import com.dyq.demo.service.DocumentService;
import com.dyq.demo.service.VoteService;
import com.dyq.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class VoteController {
    @Autowired
    private DocumentCommentService documentCommentService;
    @Autowired
    private DocumentReplyService documentReplyService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private VoteService voteService;

    @GetMapping("/document/isVote")
    public ResponseEntity<Response> isVote(Model model, HttpServletRequest request){
        Long did = Long.valueOf(request.getParameter("did"));
        Document document = documentService.findById(did);
        Vote vote = document.isVote();
        boolean isVote = false;
        if(vote.getId()!=null){
            isVote=true;
        }
        System.out.println("did"+did+",isVote:"+isVote);
        return ResponseEntity.ok().body(new Response(0,"isVote",0,isVote));
    }

    @GetMapping("/document/isDownVote")
    public ResponseEntity<Response> isDownVote(Model model, HttpServletRequest request){
        Long did = Long.valueOf(request.getParameter("did"));
        Document document = documentService.findById(did);
        DownVote vote = document.isDownVote();
        boolean isDownVote = false;
        if(vote.getId()!=null){
            isDownVote=true;
        }
        System.out.println("did"+did+",isDownVote:"+isDownVote);
        return ResponseEntity.ok().body(new Response(0,"isDownVote",0,isDownVote));
    }

    @GetMapping("/document/comment/isVote")
    public ResponseEntity<Response> isCommentVote(Model model, HttpServletRequest request){
        Long dcid = Long.valueOf(request.getParameter("dcid"));
        DocumentComment documentComment = documentCommentService.findById(dcid);
        DCVote vote = documentComment.isVote();
        boolean isVote = false;
        if(vote.getId()!=null){
            isVote=true;
        }
        System.out.println("dcid"+dcid+",isVote:"+isVote);
        return ResponseEntity.ok().body(new Response(0,"isVote",0,isVote));
    }

    @GetMapping("/document/comment/isDownVote")
    public ResponseEntity<Response> isCommentDownVote(Model model, HttpServletRequest request){
        Long dcid = Long.valueOf(request.getParameter("dcid"));
        DocumentComment documentComment = documentCommentService.findById(dcid);
        DCDownVote vote = documentComment.isDownVote();
        boolean isDownVote = false;
        if(vote.getId()!=null){
            isDownVote=true;
        }
        System.out.println("dcid"+dcid+",isDownVote:"+isDownVote);
        return ResponseEntity.ok().body(new Response(0,"isDownVote",0,isDownVote));
    }

    @PostMapping("/document/addVote")
    public ResponseEntity<Response> addDocumentVote(Model model, HttpServletRequest request) {
        Long did = Long.valueOf(request.getParameter("did"));
        Document document = documentService.addVote(did);
        boolean isVote = document.isVote().getId()!=null;
        System.out.println("did"+did+",document:"+document+",isVote:"+isVote+",voteSize:"+document.getVoteSize());
        return ResponseEntity.ok().body(new Response(0,isVote?"1":"0",0,document.getVoteSize()));
    }

    @PostMapping("/document/addDownVote")
    public ResponseEntity<Response> addDocumentDownVote(Model model, HttpServletRequest request) {
        Long did = Long.valueOf(request.getParameter("did"));
        Document document = documentService.addDownVote(did);
        boolean isDownVote = document.isVote().getId()!=null;
        System.out.println("did"+did+",document:"+document+",isDownVote:"+isDownVote+",downVoteSize:"+document.getDownVoteSize());
        return ResponseEntity.ok().body(new Response(0,isDownVote?"1":"0",0,document.getDownVoteSize()));
    }

    @PostMapping("/document/comment/addVote")
    public ResponseEntity<Response> addCommentVote(Model model, HttpServletRequest request) {
        Long dcid = Long.valueOf(request.getParameter("dcid"));
        DocumentComment documentComment = documentCommentService.addVote(dcid);
        boolean isVote = documentComment.isVote().getId()!=null;
        System.out.println("did"+dcid+",documentComment:"+documentComment+",isVote:"+isVote+",voteSize:"+documentComment.getVoteSize());
        return ResponseEntity.ok().body(new Response(0,isVote?"1":"0",0,documentComment.getVoteSize()));
    }

    @PostMapping("/document/comment/addDownVote")
    public ResponseEntity<Response> addCommentDownVote(Model model, HttpServletRequest request) {
        Long dcid = Long.valueOf(request.getParameter("dcid"));
        DocumentComment documentComment = documentCommentService.addDownVote(dcid);
        boolean isDownVote = documentComment.isDownVote().getId()!=null;
        System.out.println("dcid"+dcid+",documentComment:"+documentComment+",isDownVote:"+isDownVote+",downVoteSize:"+documentComment.getDownVoteSize());
        return ResponseEntity.ok().body(new Response(0,isDownVote?"1":"0",0,documentComment.getDownVoteSize()));
    }
}
