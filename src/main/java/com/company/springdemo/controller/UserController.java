package com.company.springdemo.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.springdemo.common.base.RespCode;
import com.company.springdemo.common.base.RespEntity;
import com.company.springdemo.common.utils.FileUtil;
import com.company.springdemo.model.ExportUserDomain;
import com.company.springdemo.model.UserDomain;
import com.company.springdemo.service.UserService;
import com.github.pagehelper.PageSerializable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Controller与RestController使用不一样，RestController的使用方法上就不用ResponseBody，默认自带
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    public int number = 2;

    @Autowired
    private UserService userService;

    /**
     * @Description: 返回类型必须为POJO类型才能自动解析成json格式
     * @Param:
     * @return:
     */
    @RequestMapping("/add")
    public RespEntity addUser(@RequestBody UserDomain user) {
        int result = userService.addUser(user);
        RespEntity respEntity;
        if (result == 1) {
            respEntity = new RespEntity(RespCode.SUCCESS, JSONObject.toJSONString(user));
        } else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR, JSONObject.toJSONString(user));
        }
        return respEntity;
    }

    @RequestMapping("/delete")
    public RespEntity deleteById(int userId) {
        log.info("用户删除：" + userId);
        boolean result = userService.deleteById(userId);
        JSONObject jsonpData = new JSONObject();
        jsonpData.put("userId", "userId");
        RespEntity respEntity;
        if (result) {
            respEntity = new RespEntity(RespCode.SUCCESS, jsonpData);
        } else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR, jsonpData);
        }
        return respEntity;
    }

    @RequestMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        number++;
        log.info("请求参数：" + number);
        log.info("请求service对象：" + userService);
        pageSize = number;
        log.info("用户查询：");
        Random rand = new Random();
        try {
            int time = rand.nextInt(5000);
            log.info("查询休息" + time + "毫秒！");
//            Thread.sleep(time);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      这里也可以照着PageInfo实现一个MyPageInfo
//        PageInfo<UserDomain>  list = userService.findAllUser(pageNum,pageSize);
        PageSerializable<UserDomain> list = userService.findAllUser(pageNum, pageSize);
        JSONObject jsonpData = new JSONObject();
        jsonpData.put("list", JSON.toJSON(list.getList()));
        jsonpData.put("total", list.getTotal());
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS, jsonpData);
        log.info("请求参数：" + number);
        return respEntity;
    }

    /**
     * @description: 该方法返回前端导出文件
     * @param:
     * @return:
     */
    @RequestMapping("/exportAllPageUser")
    public ResponseEntity<StreamingResponseBody> exportAllPageUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) throws UnsupportedEncodingException {
        PageSerializable<UserDomain> list = userService.findAllUser(pageNum, pageSize);
        try {
            String filePath = getExportDetail(list.getList());
            StreamingResponseBody responseBody = outputStream -> FileUtils.copyFile(new File(filePath), outputStream);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + new String("123.xlsx".getBytes(), "UTF-8"))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseBody);
        } catch (Exception e) {
            log.error("failed to export target file for reason:{}, export default file", e.getMessage());
            StreamingResponseBody responseBody = outputStream -> FileUtils.copyFile(new PathMatchingResourcePatternResolver().getResource("export/export-failure.xlsx").getFile(), outputStream);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + new String("123.xlsx".getBytes(), "UTF-8"))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseBody);
        }
    }

    private String getExportDetail(List<UserDomain> list) throws IOException {
        String filePath = "/Users/whs/用户信息.xlsx";
        File target = new File(filePath);
        if (target.exists()) {
            log.info("target file is exist, delete and re-create file for {}, delete result {}", filePath, FileUtil.delete(filePath));
        }
        List<ExportUserDomain> cloneList = new ArrayList<>();
        ExportUserDomain exportUserDomain = new ExportUserDomain();
        list.forEach(e -> {
            BeanUtils.copyProperties(e, exportUserDomain);
            cloneList.add(exportUserDomain);
        });
        // 根据detailInfo的内容写excel文件
        OutputStream out = new FileOutputStream(filePath);
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, ExportUserDomain.class);
        sheet1.setSheetName("用户信息展示");
        writer.write(cloneList, sheet1);
        writer.finish();
        out.close();
        return filePath;
    }

}
