package com.iplanner.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iplanner.mapper.*;
import com.iplanner.pojo.*;
import com.iplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private UserScheduleMapper userScheduleMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserTeamMapper userTeamMapper;

    // 根据邮箱查询一个用户
    @Override
    public User queryUserByEmail(String email) {
        System.out.println("email"+email);
        User user = new User();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email",email);
        user = userMapper.selectOne(userQueryWrapper);

        System.out.println("查询方法user"+user);
        return user;
    }

    // 查询用户的日程 通过id

    @Override
    public List<Schedule> queryScheduleByUserId(Long uid) {
        List<Schedule> scheduleList = new ArrayList<>();

        QueryWrapper<UserSchedule> wrapper = new QueryWrapper<>();
        QueryWrapper<Schedule> wrapper2 = new QueryWrapper<>();

        wrapper.eq("uid",uid);
        List<UserSchedule> userScheduleList = userScheduleMapper.selectList(wrapper);

        for (UserSchedule userSchedule : userScheduleList) {
            Long sid = userSchedule.getSid();
            wrapper2.eq("id", sid);
            Schedule schedule = scheduleMapper.selectOne(wrapper2);
            wrapper2.clear();
            scheduleList.add(schedule);
        }

        return scheduleList;
    }

    // 用户添加日程
    @Override
    public int addScheduleByUser(Long uid, Schedule schedule) {
        scheduleMapper.insert(schedule);
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setUid(uid);
        userSchedule.setSid(schedule.getId());
        userScheduleMapper.insert(userSchedule);
        return 1;
    }

    // 查询一个用户拥有的所有团队
    @Override
    public List<Team> queryTeamByUserId(Long uid) {
        List<Team> teamList = new ArrayList<>();

        QueryWrapper<UserTeam> utWrapper = new QueryWrapper<>();
        QueryWrapper<Team> tWrapper = new QueryWrapper<>();

        utWrapper.eq("uid", uid);
        List<UserTeam> userTeamList = userTeamMapper.selectList(utWrapper);
        for (int i = 0; i < userTeamList.size(); i++) {
            Long tid = userTeamList.get(i).getTid();
            tWrapper.eq("id",tid);
            Team team = teamMapper.selectOne(tWrapper);
            tWrapper.clear();
            teamList.add(team);
        }
        return teamList;
    }
}
