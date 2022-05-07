//package com.sjq.fdfs.test;
//
//import com.github.tobato.fastdfs.domain.StorePath;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import sun.misc.Unsafe;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author Kemp
// * @create 2022/1/6 15:08
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class fastdfsTest {
//    @Autowired
//    private FastFileStorageClient fastFileStorageClient;
//
//    /*
//    上传图片测试
//     */
//    @Test
//    public void uploda() throws FileNotFoundException {
////        http://192.168.72.138:8888/group1/M00/00/00/wKhIimHuA-eAYZyCADBBktvjuYA208.png
//        File f = new File("C:\\Users\\Kemp\\Pictures\\Saved Pictures\\s.jpg");
//        FileInputStream fi = new FileInputStream(f);
//        StorePath storePath = fastFileStorageClient.uploadFile(fi, f.length(), "jpg", null);
//        System.out.println(storePath.getGroup());
//        System.out.println(storePath.getPath());
//        System.out.println(storePath.getFullPath());
//    }
//
//    public void test(){
//        Unsafe un = Unsafe.getUnsafe();
//    }
//
//}
