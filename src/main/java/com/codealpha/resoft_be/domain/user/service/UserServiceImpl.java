package com.codealpha.resoft_be.domain.user.service;

import com.codealpha.resoft_be.domain.user.dto.Request;
import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    /**
     * @TODO: 추후에 encrypt 추가 및 ResponseDTO 추가
     * */
    @Override
    public User register(Request.Register registerCmd) {
        User createdUser = User.create(registerCmd.getName(), registerCmd.getEmail(), registerCmd.getPassword());
        return userRepository.save(createdUser);
    }
}
