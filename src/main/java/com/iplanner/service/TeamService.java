package com.iplanner.service;

import com.iplanner.pojo.Schedule;
import com.iplanner.pojo.Team;
import com.iplanner.pojo.User;

import java.util.List;

public interface TeamService {

    Team queryTeamById(String id);

    List<Schedule> queryScheduleByTeamId(Long tid);

    int addScheduleByTeam(Long tid, Schedule schedule);

    List<User> queryUserByTeamId(Long tid);
}
