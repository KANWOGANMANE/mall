package com.sjq.fdfs.store.service;

import com.sjq.fdfs.store.entity.DleVideoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author Kemp
 * @create 2022/1/24 9:55
 */

public interface FdfsService {

    String uploadFileavatar(MultipartFile file);

    Map<String,String> uploadFileVideo(MultipartFile file);

    void deleteFileByGrouppath(DleVideoVo groupath);

    void deleteBath(List<String> pathList);
}
