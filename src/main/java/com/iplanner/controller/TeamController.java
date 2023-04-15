package com.iplanner.controller;

import com.iplanner.pojo.Schedule;
import com.iplanner.pojo.Team;
import com.iplanner.pojo.User;
import com.iplanner.service.TeamServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamServiceImpl teamService;

    @GetMapping("/getTeam/{id}")
    @ResponseBody
    public Team getTeam(@PathVariable("id") String id){
        Team team = teamService.queryTeamById(id);
        return team;
    }

    @GetMapping("/team/showSchedule/{id}")
    @ResponseBody
    public List<Schedule> showSchedule (@PathVariable("id") Long tid){
        List<Schedule> schedules = teamService.queryScheduleByTeamId(tid);
        return schedules;
    }

    @PostMapping("/team/addSchedule")
    @ResponseBody
    public void addScheduleByTeam(@RequestBody JSONObject params){

        System.out.println(params);
        Long tid = Long.parseLong(params.get("tid").toString()) ;

        Schedule schedule = new Schedule();
        String title = params.get("title").toString();
        schedule.setTitle(title);

        String details = params.get("details").toString();
        schedule.setDetails(details);

        Long start1 =Long.parseLong(params.get("start").toString());
        Date start = new Date(start1);
        schedule.setStart(start);

        Long end1 =Long.parseLong(params.get("end").toString());
        Date end = new Date(end1);
        schedule.setEnd(end);

        System.out.println(schedule);
        teamService.addScheduleByTeam(tid, schedule);
    }

    @GetMapping("/team/showUser/{id}")
    @ResponseBody
    public List<User> showUser(@PathVariable("id") Long tid){
        List<User> users = teamService.queryUserByTeamId(tid);
        return users;
    }

}
