package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DocMapper;
import com.kaishengit.pojo.Doc;
import com.kaishengit.security.SecurityUtil;
import com.kaishengit.service.DocService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/18.
 */
@Service
@Transactional
public class DocServiceImpl implements DocService {
    @Autowired
    private DocMapper docMapper;
    @Value("${upload.path}")
    private String savePath;

    @Override
    public List<Doc> findByFid(Integer fid) {
        return (List<Doc>) docMapper.findByFid(fid);
    }

    @Override
    public void saveDir(String name, Integer fid) {
        Doc doc = new Doc();
        doc.setName(name);
        doc.setCreateuser(SecurityUtil.getCurrentUserName());
        doc.setFid(fid);
        doc.setType(Doc.TYPE_DIR);
        docMapper.save(doc);
    }

    /*
    * * 保存文件
     * @param inputStream 文件输入流
     * @param originalFilename 文件真实名称
     * @param contentType 文件MIME类型
     * @param size 文件大小(字节)
     * @param fid 父ID
     * */
    @Override
    public void saveFile(MultipartFile file, Integer fid) {
        String name = file.getOriginalFilename();
        //原文件名后面的后缀
        String lastName= "";
        if(name.lastIndexOf(".") != -1){
            lastName = name.substring(name.lastIndexOf("."));
        }
        String newName = UUID.randomUUID().toString()+lastName;
        try {
            File saveFile = new File(new File(savePath), newName);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            InputStream inputStream = file.getInputStream();
            /*InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(new File(savePath,name));*/
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            throw new ServiceException("文件保存到磁盘异常",ex);
        }
        Doc doc = new Doc();
        doc.setCreateuser(SecurityUtil.getCurrentUserName());
        doc.setName(name);
        doc.setType(Doc.TYPE_DOC);
        doc.setFid(fid);
        doc.setSize(FileUtils.byteCountToDisplaySize(file.getSize()));
        doc.setFilename(newName);
        doc.setContexttype(file.getContentType());
        docMapper.update(doc);
    }

    @Override
    public Doc findById(Integer id) {
        return docMapper.findById(id);
    }

    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
        Doc doc = findById(id);
        if(doc == null || Doc.TYPE_DIR.equals(doc.getType())){
            return null;
        }else {
            FileInputStream inputStream = new FileInputStream(new File(new File(savePath), doc.getFilename()));
            return inputStream;
        }
    }
    @Override
    public void delById(Integer id) {
        Doc doc = docMapper.findById(id);
        if(doc != null) {
            if (Doc.TYPE_DOC.equals(doc.getType())) {
                //删除文件
                File file = new File(savePath, doc.getFilename());
                file.delete();
                //删除数据库记录
                docMapper.delete(id);
            } else {
                List<Doc> docList = docMapper.findAll();
                List<Integer> delList = Lists.newArrayList();
                findDelId(docList,delList,id);
                docMapper.delete(id);
              /*  //批量删除
                docDao.batchDel(delList);*/
            }
        }
    }

    private void findDelId(List<Doc> docList,
                           List<Integer> delIdList, Integer id) {
        for(Doc doc : docList) {
            if(doc.getFid().equals(id)) {
                docMapper.delete(doc.getId());
                if(doc.getType().equals(Doc.TYPE_DIR)){
                    findDelId(docList,delIdList,doc.getId());
                } else {
                    File file = new File(savePath,doc.getFilename());
                    file.delete();
                }
            }
        }
    }

}
