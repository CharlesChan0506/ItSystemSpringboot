package com.charles.itsystem.service;

import com.charles.itsystem.vo.DocumentPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DocumentService {

    int addDocument(MultipartFile file, Integer staffID, Integer depID);  //上传文件并保存信息到数据库
    DocumentPage selectDocumentList(Integer current, Integer size); //查询所有文件并分页
    String downLoadDocument(Integer documentID, HttpServletResponse response);  //根据资料ID下载资料
}
