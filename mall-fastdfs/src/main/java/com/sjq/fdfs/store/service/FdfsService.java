package com.sjq.fdfs.store.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Kemp
 * @create 2022/1/24 9:55
 */

public interface FdfsService {

    String uploadFileavatar(MultipartFile file);

    String uploadFileVideo(MultipartFile file);
}
