package cn.yujian95.hospital.controller;

import cn.yujian95.hospital.common.api.CommonResult;
import cn.yujian95.hospital.component.QiniuComponent;
import cn.yujian95.hospital.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 
 * @date 2020/2/6
 */
@Api(value = "图片模块", tags = "图片接口")
@RestController
@CrossOrigin
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private QiniuComponent qiniuComponent;
    @Resource
    private IFileService iFileService;

    @ApiOperation(value = "上传图片，返回图片url", notes = "传入 图片文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult<String> uploadPicture(@RequestParam MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return CommonResult.validateFailed("上传图片为空！");
        }
        //七牛上传
        //String url = qiniuComponent.uploadFile(file);
        //本地上传
        String url = iFileService.uploadFile(file);
        if (!StringUtils.isEmpty(url)) {
            return CommonResult.success(url);
        }

        return CommonResult.failed("服务器错误，请联系管理员！");
    }
}
