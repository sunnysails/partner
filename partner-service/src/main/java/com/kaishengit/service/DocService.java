package com.kaishengit.service;


import com.kaishengit.pojo.Doc;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public interface DocService {

    List<Doc> findByFid(Integer fid);

    void saveDir(String name, Integer fid);

    void saveFile(MultipartFile file, Integer fid);

    Doc findById(Integer id);

    InputStream downloadFile(Integer id) throws FileNotFoundException;
}
