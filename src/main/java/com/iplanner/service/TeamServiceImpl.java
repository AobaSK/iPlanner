package com.iplanner.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iplanner.mapper.*;
import com.iplanner.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private TeamScheduleMapper teamScheduleMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private UserMapper userMapper;

    // 根据id查一个团队
    @Override
    public Team queryTeamById(String id) {
        Team team = new Team();
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>();
        teamQueryWrapper.eq("id",id);
        team = teamMapper.selectOne(teamQueryWrapper);
        return team;
    }

    // 通过id查询团队的日程
    @Override
    public List<Schedule> queryScheduleByTeamId(Long tid) {
        List<Schedule> scheduleList = new ArrayList<>();

        QueryWrapper<TeamSchedule> teamScheduleWrapper = new QueryWrapper<>();
        QueryWrapper<Schedule> scheduleWrapper = new QueryWrapper<>();

        teamScheduleWrapper.eq("tid",tid);
        List<TeamSchedule> teamScheduleList = teamScheduleMapper.selectList(teamScheduleWrapper);

        for (int i = 0; i < teamScheduleList.size(); i++) {
            Long sid = teamScheduleList.get(i).getSid();
            scheduleWrapper.eq("id",sid);
            Schedule schedule = scheduleMapper.selectOne(scheduleWrapper);
            scheduleWrapper.clear();
            scheduleList.add(schedule);
        }

        return scheduleList;
    }

    // 添加团队日程
    @Override
    public int addScheduleByTeam(Long tid, Schedule schedule) {
        scheduleMapper.insert(schedule);
        TeamSchedule teamSchedule = new TeamSchedule();
        teamSchedule.setTid(tid);
        teamSchedule.setSid(schedule.getId());
        teamScheduleMapper.insert(teamSchedule);
        return 1;
    }

    // 查询团队成员
    @Override
    public List<User> queryUserByTeamId(Long tid) {
        List<User> userList = new ArrayList<>();

        QueryWrapper<UserTeam> userTeamWrapper = new QueryWrapper<>();
        QueryWrapper<User> userWrapper = new QueryWrapper<>();

        userTeamWrapper.eq("tid",tid);
        List<UserTeam> userTeamList = userTeamMapper.selectList(userTeamWrapper);
        for (int i = 0; i < userTeamList.size(); i++) {
            Long uid = userTeamList.get(i).getUid();
            userWrapper.eq("id",uid);
            User user = userMapper.selectOne(userWrapper);
            userWrapper.clear();
            userList.add(user);
        }
        return userList;
    }
}
