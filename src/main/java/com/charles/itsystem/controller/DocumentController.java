package com.charles.itsystem.controller;

import com.charles.itsystem.service.DocumentService;
import com.charles.itsystem.vo.DocumentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/document")
@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    //上传文件并保存信息到数据库
    @PostMapping("/add")
    public int addDocument(@RequestParam(value = "file",required = false) MultipartFile file, Integer staffID, Integer depID){
        return documentService.addDocument(file,staffID,depID);
    }
    //查询所有文件并分页
    @GetMapping("/{current}/{size}")
    public DocumentPage selectDocumentList(@PathVariable Integer current, @PathVariable Integer size){
        return documentService.selectDocumentList(current, size);
    }
    //根据资料ID下载资料
    @GetMapping("/download/{documentID}")
    public String downLoadDocument(@PathVariable Integer documentID, HttpServletResponse response){
        return documentService.downLoadDocument(documentID,response);
    }
}
