package com.codealpha.resoft_be.domain.user.service;

import com.codealpha.resoft_be.domain.user.dto.Request;
import com.codealpha.resoft_be.domain.user.entity.User;

public interface UserService {
    User register(Request.Register registerCmd);

}
