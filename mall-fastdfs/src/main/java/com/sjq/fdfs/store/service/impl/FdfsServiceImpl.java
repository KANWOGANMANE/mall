package com.sjq.fdfs.store.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.sjq.fdfs.store.service.FdfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sjq.fdfs.store.config.ServeDefaultData;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author Kemp
 * @create 2022/1/24 9:55
 */
@Service
public class FdfsServiceImpl implements FdfsService {
    private static final Logger logger = LoggerFactory.getLogger(FdfsServiceImpl.class);

    private String[] support_image_type = new String[]{"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"};

    //http://192.168.72.138:8888/
    private String prefix= ServeDefaultData.FPORTAL+ "://" + ServeDefaultData.FIP + ":" +ServeDefaultData.FPORT + "/";

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    //判断是否是图片格式
    private boolean isSupportImage(String fileExtName) {
        boolean flag = Arrays.asList(support_image_type).contains(fileExtName.toUpperCase());
        return flag;
    }


    //上传图片
    @Override
    public String uploadFileavatar(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileExtName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), fileExtName, null);
            String fullPath = storePath.getFullPath();
            String res = prefix + fullPath;
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //上传视频
    @Override
    public String uploadFileVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();   //上传文件的原名称
            String contentType = file.getContentType();
            String fileExtName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); //扩展名称
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), fileExtName, null);
            String fullPath = storePath.getFullPath();
            return null;
        }catch(Exception e){
            return null;
        }
    }
}
