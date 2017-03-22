package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Doc;
import com.kaishengit.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/doc")
public class DocumentController {
    @Autowired
    private DocService docService;

    /*
    * 根据fid查找文件和文件夹列表
    * */
    @GetMapping
    public String list(@RequestParam(required = false,defaultValue = "0") Integer fid,
            Model model){
        model.addAttribute("documentList",docService.findByFid(fid));
        model.addAttribute("fid",fid);
        return "document/list";
    }
    /*
    * 保存文件夹
    * */
    @PostMapping("/dir/new")
    public String saveDir(String name,Integer fid){
        docService.saveDir(name,fid);
        return "redirect:/doc"+"?fid="+fid;
    }

    @PostMapping("/file/upload")
    @ResponseBody
    public String saveDoc(MultipartFile file, Integer fid)throws IOException{

        if(file.isEmpty()) {
            throw new NotFoundException();
        } else {
            docService.saveFile(file,fid);
            return "success";
        }
    }

    @GetMapping("/download/{id:\\d+}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> upload(@PathVariable Integer id) throws FileNotFoundException {
        InputStream inputStream = docService.downloadFile(id);
        Doc doc = docService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachement",doc.getName(), Charset.forName("UTF-8"));

        return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);

    }

    /*TODO
    * */
    @GetMapping("/del/{id:\\d+}")
    @ResponseBody
    public AjaxResult del(@PathVariable Integer id) {
        docService.delById(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }
}