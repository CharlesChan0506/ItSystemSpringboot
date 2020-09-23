package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.Department;
import com.charles.itsystem.entity.Document;
import com.charles.itsystem.entity.Staff;
import com.charles.itsystem.mapper.DepartmentMapper;
import com.charles.itsystem.mapper.DocumentMapper;
import com.charles.itsystem.mapper.StaffMapper;
import com.charles.itsystem.util.MyUtil;
import com.charles.itsystem.vo.DocumentPage;
import com.charles.itsystem.vo.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("DocumentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 上传文件并保存信息到数据库
     *
     * @param file
     * @param staffID
     * @param depID
     * @return
     */
    @Override
    public int addDocument(MultipartFile file, Integer staffID, Integer depID) {
        try {
            // 获取原始名字
            String docuName = file.getOriginalFilename();
            // 获取后缀名
            String docuType = docuName.substring(docuName.lastIndexOf("."));
            //设置保存文件名字
            String fileName =  UUID.randomUUID() + "_" + docuName;
            //获取文件大小
            String fileSize = MyUtil.getDocumentSize(file.getSize());
            // 文件保存路径
            String filePath = "D:\\itSystem_Document\\";

            File newFile = new File(filePath, fileName);
            //判断是否存在该路径文件夹，不存在就创建
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            // 保存到服务器中
            file.transferTo(newFile);

////            //获取员工ID和部门ID
////            QueryWrapper<Staff> wrapper = new QueryWrapper<>();
////            wrapper.eq("userID", (Integer) session.getAttribute("userID"));
//            new Staff();
//            Staff staff = staffMapper.selectOne(wrapper);

            //存入数据库
            Document document = new Document();
            document.setStaffID(staffID);
            document.setDepID(depID);
            document.setDocuName(docuName);
            document.setDocuType(docuType);
            document.setFileName(fileName);
            document.setFileSize(fileSize);
            document.setFilePath(filePath);
            document.setCreateTime(new Date());

            return documentMapper.insert(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询所有文件并分页
     *
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public DocumentPage selectDocumentList(Integer current, Integer size) {
        DocumentPage documentPage = new DocumentPage();
        List<DocumentVO> documentVOList = new ArrayList<>();

        Page<Document> page = new Page<>(current,size);
        QueryWrapper<Document> wrapper = new QueryWrapper<>();
        IPage<Document> iPage = documentMapper.selectPage(page,wrapper);
        List<Document> documents = iPage.getRecords();
        documentPage.setTotal(iPage.getTotal());  //设置总记录数

        for (Document document : documents) {
            new Department();
            Department department = departmentMapper.selectById(document.getDepID());

            new Staff();
            Staff staff = staffMapper.selectById(document.getStaffID());

            DocumentVO documentVO = new DocumentVO();
            documentVO.setDocumentID(document.getDocumentID());
            documentVO.setStaffName(staff.getStaffName());
            documentVO.setDepName(department.getDepName());
            documentVO.setDocuName(document.getDocuName());
            documentVO.setDocuType(document.getDocuType());
            documentVO.setFileSize(document.getFileSize());
            documentVO.setCreateTime(MyUtil.transfoDate(document.getCreateTime()));

            documentVOList.add(documentVO);
        }
        documentPage.setRecords(documentVOList);

        return documentPage;
    }

    @Override
    public String downLoadDocument(Integer documentID, HttpServletResponse response) {
        try {
            new Document();
            Document document = documentMapper.selectById(documentID);

            String docuName = URLEncoder.encode(document.getDocuName(),"UTF-8");
            File file = new File(document.getFilePath(), document.getFileName());
            if (file.exists()) {
                //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
                response.setContentType("application/force-download");
                //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
                response.addHeader("Content-Disposition", "attachment; filename=" + docuName);
                //进行读写操作
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    //从源文件中读
                    int i = bis.read(buffer);
                    while (i != -1) {
                        //写到response的输出流中
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //善后工作，关闭各种流
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}