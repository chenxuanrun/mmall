package com.mmall.util;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @program: mmall
 * @description: FTPUtil
 * @author: cxr
 * @create: 2019-08-12 19:45
 **/
@Data
@ToString
@Accessors(chain = true)
@Slf4j
public class FTPUtil {
    private final static String FTP_IP=PropertiesUtil.getProperty("ftp.service.ip");
    private final static String FTP_USER=PropertiesUtil.getProperty("ftp.user");
    private final static String FTP_PASS=PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(FTP_IP,21,FTP_USER,FTP_PASS);
        log.info("开始连接ftp服务器");
        boolean result=ftpUtil.uploadFile("img",fileList);
        log.info("结束上传,上传结果:{}",result);
        return result;
    }
    
    private boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded=true;
        FileInputStream fls=null;
        //连接ftp服务器
        if (connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);    
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem:fileList){
                    fls = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fls);
                }
            } catch (IOException e) {
                log.error("上传文件异常",e);
                uploaded=false;
            }catch (Exception e){
                log.error("上传文件异常",e);
                uploaded=false;
            }finally {
                fls.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }
    
    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;
    
    private boolean connectServer(String ip,int port,String user,String pwd){
        boolean isSuccess=false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
            
        } catch (IOException e) {
          log.error("连接ftp服务器异常",e);
            return isSuccess;
        }
        return isSuccess;
    }
}
