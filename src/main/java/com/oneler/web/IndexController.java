package com.oneler.web;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import javax.transaction.Transactional;@RestController@RequestMapping("/")public class IndexController {    @RequestMapping("/")    public String index() {        return "hello world ~~~";    }}