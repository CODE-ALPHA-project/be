package com.codealpha.resoft_be.domain.user;

import com.codealpha.resoft_be.domain.user.dto.Request;
import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.repository.UserRepository;
import com.codealpha.resoft_be.domain.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    // Dummy User 생성 메서드
    User createDummyUser(Long id, String name, String email, String password) {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    @BeforeEach
    public void 설정_테스트() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("유저를 성공적으로 가입하는 테스트")
    void 유저_가입_테스트() {
        // Given: 테스트에 필요한 입력 데이터와 예상되는 출력 데이터를 준비합니다.
        Request.Register registerCmd = new Request.Register("taehyun", "taehyun@example.com", "password123");
        User savedUser = createDummyUser(1L, "taehyun", "taehyun@example.com", "password123");

        // Repository의 save 메서드가 호출되면 savedUser를 반환하도록 Mock 설정
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When: 테스트 대상 메서드 실행
        User result = userService.register(registerCmd);

        // Then: 예상 결과와 실제 결과를 검증
        Assertions.assertEquals(savedUser, result);  // savedUser와 result가 같은지 비교
        Assertions.assertEquals(savedUser.getId(), result.getId());
        Assertions.assertEquals(savedUser.getName(), result.getName());
        Assertions.assertEquals(savedUser.getEmail(), result.getEmail());
        Assertions.assertEquals(savedUser.getPassword(), result.getPassword());

        // Repository의 save 메서드가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).save(any(User.class));
    }
}
