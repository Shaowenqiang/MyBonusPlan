package com.swq.action;

import com.swq.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;



@Controller
@RequestMapping("/user")
public class UserAction {
    Logger logger = LogManager.getLogger(UserAction.class);
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/createNum")
    public List createNum() {
        return service.createNum();
    }
}
