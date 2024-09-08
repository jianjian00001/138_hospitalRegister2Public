package cn.yujian95.hospital.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.yujian95.hospital.common.FilePathUtil;
import cn.yujian95.hospital.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Ynw
 * @Date: 2024/04/21/16:38
 * @Description:
 */
@Service
public class IFileServiceImpl implements IFileService {
    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String  contextpath;

    public String uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = UUID.randomUUID().toString().replace("-", "");
        //String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        // 获取文件的后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String rootFilePath = FilePathUtil.filePath() + flag + suffixName;
        File saveFile = new File(rootFilePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return "http://localhost:"+port+contextpath+"/file/"+flag + suffixName;
    }
}
