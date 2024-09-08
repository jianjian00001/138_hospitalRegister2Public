package cn.yujian95.hospital.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Ynw
 * @Date: 2024/04/21/16:37
 * @Description:
 */
public interface IFileService {
    String uploadFile( MultipartFile file) throws IOException;
}
