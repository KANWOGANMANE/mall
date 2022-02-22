package com.sjq.store.service;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Kemp
 * @create 2022/1/24 9:55
 */
public class FdfsServiceImpl implements FdfsService{

    private String[] support_image_type = new String[]{"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"};

    @Autowired
    private FastFileStorageClient fastFileStorageClient;




}
