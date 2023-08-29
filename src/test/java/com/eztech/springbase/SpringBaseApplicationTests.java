package com.eztech.springbase;

import com.eztech.springbase.entity.User;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.vo.user.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBaseApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void mapstruct()
    {
        User user = new User();
        user.setUsername("account");
        user.setNickname("admin");
        user.setPassword("1234");

        UserVo userVo = UserMapper.INSTANCE.userToVo(user);

        System.out.println(userVo);
    }

}
