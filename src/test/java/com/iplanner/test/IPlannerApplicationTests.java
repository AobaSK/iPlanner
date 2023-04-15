package com.iplanner.test;

import com.iplanner.mapper.*;
import com.iplanner.pojo.Team;
import com.iplanner.pojo.User;
import com.iplanner.pojo.UserTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPlannerApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private UserScheduleMapper userScheduleMapper;
    @Autowired
    private TeamScheduleMapper teamScheduleMapper;

    @Test
    public void insertUser(){
        User user = new User();
        user.setUsername("Aoba");
        user.setPassword("1");
        user.setEmail("1");
        user.setPhone("15536933629");
        user.setProfession("Java开发");
        userMapper.insert(user);
    }

    @Test
    public void insertTeam(){
        Team team = new Team();
        team.setTeamname("实验室小组");
        team.setCreator(1644986003782688769L);
        team.setType("工作");
        teamMapper.insert(team);
    }

    @Test
    public void insertUserTeam(){
        UserTeam userTeam = new UserTeam();
        userTeam.setUid(1644986003782688769L);
        userTeam.setTid(1645002301325524994L);
        userTeamMapper.insert(userTeam);
    }

    @Test
    public void test(int A, int B, int X){
        if (A<5 && B == 5){
            X = X / A;
        }
        if (A==2 || X > 2){
            X = X + 1;
        }
    }
}
