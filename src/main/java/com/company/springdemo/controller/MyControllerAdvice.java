package com.company.springdemo.controller;

import com.company.springdemo.common.utils.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: whs
 * @Date: 2019/1/11 17:34
 * @Description: 注解的使用
 * 地址：https://www.cnblogs.com/magicalSam/p/7198420.html
 */
@RestController
public class MyControllerAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "whs");
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    // 该model类仅限于该controller类中，其他controller无法使用
    @RequestMapping("/home")
    public String home(@ModelAttribute("author") String author) {
        System.out.println(author);
        return author;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }

    /** 
     * @Description: 该方法异常处理该controler异常
     * @Param: 
     * @return:
     */ 
    @RequestMapping("/testException")
    public String testException() {
        throw new MyException("101", "Sam 错误");

    }

    /** 
     * @Description: 该方法 ModelAndView返回异常
     * @Param: 
     * @return:
     */ 
    @ExceptionHandler(value = MyException.class)
    public ModelAndView myMVErrorHandler(MyException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }

}
