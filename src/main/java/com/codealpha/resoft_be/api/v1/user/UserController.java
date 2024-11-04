package com.codealpha.resoft_be.api.v1.user;

import com.codealpha.resoft_be.domain.user.dto.Request;
import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.service.UserService;
import com.codealpha.resoft_be.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public User register(@RequestBody Request.Register registerCmd){
        return userService.register(registerCmd);
    }
    /**
     * TODO: find,update delete 기능은 추후 구현
     *
     */
//    public void find(){
//
//    }
//    public void update(){}
//
//    public void delete(){
//
//    }
}
