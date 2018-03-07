package com.dyq.demo.controller;

import com.dyq.demo.domain.Authority;
import com.dyq.demo.domain.Document;
import com.dyq.demo.domain.ES.ESDocument;
import com.dyq.demo.domain.Reward;
import com.dyq.demo.domain.User;
import com.dyq.demo.service.AuthorityService;
import com.dyq.demo.service.DocumentService;
import com.dyq.demo.service.ES.ESDocumentService;
import com.dyq.demo.service.RewardService;
import com.dyq.demo.service.UserService;
import com.dyq.demo.util.HttpRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 *
 */
@Controller
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ESDocumentService esDocumentService;

    @GetMapping("/document")
    public String document(Model model,
                        @RequestParam(value="async",required=false) boolean async,
                        @RequestParam(value="page",required=false,defaultValue="1") int pageIndex,
                        @RequestParam(value="limit",required=false,defaultValue="2") int pageSize,
                        @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
                        @RequestParam(value="category",required=false,defaultValue="default") String type,
                        @RequestParam(value="mode",required=false,defaultValue="custom") String mode) {

        System.out.println("pageIndex:"+pageIndex+"pageSize:"+pageSize+"type:"+type+",async:"+async+",keyword:"+keyword);

        //综合
        List<ESDocument> customDocuments = null;
        Page<ESDocument> customPages = null;
        long customCount=0;
        Pageable customPageable = new PageRequest(pageIndex-1, pageSize);
        if(mode.equalsIgnoreCase("custom") || !async){
            if(type.equalsIgnoreCase("default")){
                customPages = esDocumentService.getHotestESDocuments(null,keyword,customPageable);
                customDocuments = customPages.getContent();
                customCount =   customPages.getTotalElements();
            }else {
                customPages = esDocumentService.getHotestESDocuments(type,keyword,customPageable);
                customDocuments = customPages.getContent();
                customCount = customPages.getTotalElements();
            }
        }
        model.addAttribute("customDocuments",customDocuments);
        model.addAttribute("customCount",customCount);
        System.out.println("customDocuments:"+customDocuments);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user);

        System.out.println(async?"/document :: #"+mode+"ContainerSysc":"/document");
        return (async?"/document :: #"+mode+"ContainerSysc":"/document");
    }


    /*@GetMapping("/documentdetail/{did}")
    public String documentdetail(@PathVariable Long did,Model model) {
        ESDocument esDocument = esDocumentService.getESDocumentByDocumentId(did);
        model.addAttribute("esDocument",esDocument);
        System.out.println("esDocument:"+esDocument);
        return "/documentdetail";
    }*/

    @GetMapping("/documentdetail")
    public String documentdetail(Long did,Model model) {
        System.out.println("did:"+did);
        ESDocument esDocument = esDocumentService.getESDocumentByDocumentId(did);
        String documentContent = HttpRequest.sendGet(esDocument.getFilepath(),null);
        model.addAttribute("esDocument",esDocument);
        System.out.println("esDocument:"+esDocument);
        model.addAttribute("documentContent",documentContent);
        System.out.println("documentContent:"+documentContent);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user);
        return "/documentdetail";
    }


    /**
     * 上传文档页面
     * @return
     */
    @GetMapping("/uploaddocument")
    public String uploaddocument() {
        return "uploaddocument";
    }

    /**
     * 保存上传文档
     * @param document
     * @return
     */
    @PostMapping("/upload/document")
    public String uploaddocument(Document document) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        document.setDocumentUser(principal);
        System.out.println(document);
        documentService.save(document);
        return "redirect:/index";
    }


    /**
     * 返回悬赏任务页面
     * @return
     */
    @GetMapping("/rewardtask")
    public String rewardtask() {
        return "rewardtask";
    }

    /**
     * 保存悬赏任务
     * @param reward
     * @return
     */
    @PostMapping("/rewardtask")
    public String rewardtask(Reward reward) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reward.setRewardUser(principal);
        System.out.println(reward);
        rewardService.save(reward);
        return "redirect:/index";
    }

    @GetMapping("/document/delete/{id}")
    public String deleteDocument(@PathVariable Long id) {
        documentService.remove(id);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model,
                              @RequestParam(value="async",required=false) boolean async,
                        @RequestParam(value="page",required=false,defaultValue="1") int pageIndex,
                        @RequestParam(value="limit",required=false,defaultValue="2") int pageSize,
                        @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
                        @RequestParam(value="category",required=false,defaultValue="default") String type,
                        @RequestParam(value="mode",required=false,defaultValue="custom") String mode) {

        System.out.println("pageIndex:"+pageIndex+"pageSize:"+pageSize+"type:"+type+",async:"+async+",keyword:"+keyword);

        //综合
        List<ESDocument> customDocuments = null;
        Page<ESDocument> customPages = null;
        long customCount=0;
        Pageable customPageable = new PageRequest(pageIndex-1, pageSize);
        if(mode.equalsIgnoreCase("custom") || !async){
            if(type.equalsIgnoreCase("default")){
                customPages = esDocumentService.getCustomESDocuments(null,keyword,customPageable);
                customDocuments = customPages.getContent();
                customCount =   customPages.getTotalElements();
            }else {
                customPages = esDocumentService.getCustomESDocuments(type,keyword,customPageable);
                customDocuments = customPages.getContent();
                customCount = customPages.getTotalElements();
            }
        }
        //最新
        List<ESDocument> newDocuments = null;
        Page<ESDocument> newPages = null;
        long newCount=0;
        Sort newSort = new Sort(Sort.Direction.DESC,"createdAt");
        Pageable newPageable = new PageRequest(pageIndex-1,pageSize,newSort);
        if(mode.equalsIgnoreCase("new") || !async) {
            if(type.equalsIgnoreCase("default")){
                newPages =esDocumentService.getNewestESDocuments(null,keyword,newPageable);
                newDocuments = newPages.getContent();
                newCount = newPages.getTotalElements();
            }else {
                newPages =esDocumentService.getNewestESDocuments(type,keyword,newPageable);
                newDocuments = newPages.getContent();
                newCount = newPages.getTotalElements();
            }
        }

        //最热
        List<ESDocument> hotESDocuments = null;
        Page<ESDocument> hotPages = null;
        long hotCount=0;
        Sort hotSort = new Sort(Sort.Direction.DESC,"createdAt");
        Pageable hotPageable = new PageRequest(pageIndex-1, pageSize, hotSort);
        if(mode.equalsIgnoreCase("hot") || !async) {
            if(type.equalsIgnoreCase("default")){
                hotPages = esDocumentService.getHotestESDocuments(null,keyword,hotPageable);
                hotESDocuments = hotPages.getContent();
                hotCount = hotPages.getTotalElements();
            }else {
                hotPages = esDocumentService.getHotestESDocuments(type,keyword,hotPageable);
                hotESDocuments = hotPages.getContent();
                hotCount = hotPages.getTotalElements();
            }
        }

        if(!async){
            model.addAttribute("customDocuments",customDocuments);
            model.addAttribute("newDocuments",newDocuments);
            model.addAttribute("hotDocuments",hotESDocuments);
            model.addAttribute("customCount",customCount);
            model.addAttribute("newCount",newCount);
            model.addAttribute("hotCount",hotCount);
            System.out.println("customDocuments:"+customDocuments);
            System.out.println("newDocuments:"+newDocuments);
            System.out.println("hotDocuments:"+hotESDocuments);
        }else{
            if(mode.equalsIgnoreCase("custom")){
                model.addAttribute("customDocuments",customDocuments);
                model.addAttribute("customCount",customCount);
                System.out.println("customDocuments:"+customDocuments);
            }else if(mode.equalsIgnoreCase("new")){
                model.addAttribute("newDocuments",newDocuments);
                model.addAttribute("newCount",newCount);
                System.out.println("newDocuments:"+newDocuments);
            }else if(mode.equalsIgnoreCase("hot")){
                model.addAttribute("hotDocuments",hotESDocuments);
                model.addAttribute("hotCount",hotCount);
                System.out.println("hotDocuments:"+hotESDocuments);
            }
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user);

        System.out.println(async?"/index :: #"+mode+"ContainerSysc":"/index");
        return (async?"/index::#"+mode+"ContainerSysc":"/index");
        //return new ModelAndView("/index");
    }

    @GetMapping("/show/teacher")
    public ResponseEntity<Response> showTeacher(@RequestParam(value="page",required=false,defaultValue="1") int pageIndex,
                                                 @RequestParam(value="limit",required=false,defaultValue="0") int pageSize,
                                                 @RequestParam(value="category",required=false,defaultValue="") String type,
                                                 @RequestParam(value="mode",required=false,defaultValue="custom") String mode){
        Sort sort = new Sort(Sort.Direction.DESC,"created_at");
        Pageable pageable = new PageRequest(pageIndex-1,pageSize);
        List<User> users = null;
        if(mode.equalsIgnoreCase("custom")){

        }else if(mode.equalsIgnoreCase("new")){

        }else if(mode.equalsIgnoreCase("hot")){

        }
        System.out.println(users);
        return ResponseEntity.ok().body(new Response(0,"",0,users));
    }
}
