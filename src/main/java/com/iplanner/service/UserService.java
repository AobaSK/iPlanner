package com.iplanner.service;

import com.iplanner.pojo.Schedule;
import com.iplanner.pojo.Team;
import com.iplanner.pojo.User;

import java.util.List;

public interface UserService {

    User queryUserByEmail(String email);

    List<Schedule> queryScheduleByUserId(Long uid);

    int addScheduleByUser(Long uid, Schedule schedule);

    List<Team> queryTeamByUserId(Long uid);

    Long insertUser(User user);

}
