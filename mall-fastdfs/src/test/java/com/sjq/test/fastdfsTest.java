package com.sjq.test;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kemp
 * @create 2022/1/6 15:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class fastdfsTest {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /*
    上传图片测试
     */
    @Test
    public void uploda() throws FileNotFoundException {

        File f = new File("d://mv.mp4");
        FileInputStream fi = new FileInputStream(f);
        StorePath storePath = fastFileStorageClient.uploadFile(fi, f.length(), "mp4", null);
        System.out.println(storePath.getGroup());
        System.out.println(storePath.getPath());
        System.out.println(storePath.getFullPath());
    }

    public void test(){
        Unsafe un = Unsafe.getUnsafe();
    }

}
