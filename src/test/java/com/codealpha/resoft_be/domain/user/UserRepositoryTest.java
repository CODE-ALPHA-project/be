package com.codealpha.resoft_be.domain.user;

import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    User createDummyUser(String name, String email, String password) {
        return User.create(name, email, password);
    }

    @Nested
    @DisplayName("유저 가입 테스트")
    class 유저가입테스트 {

        @Test
        @DisplayName("유저 가입 성공 테스트")
        void 유저_가입_성공() {
            //given
            User user = createDummyUser("name", "email", "password");

            //when
            User savedUser = userRepository.save(user);

            //then
            Assertions.assertEquals(savedUser.getName(), "name");
            Assertions.assertEquals(savedUser.getEmail(), "email");
            Assertions.assertEquals(savedUser.getPassword(), "password");
        }
    }

}
