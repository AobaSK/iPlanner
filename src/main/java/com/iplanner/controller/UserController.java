package com.iplanner.controller;

import com.iplanner.pojo.Schedule;
import com.iplanner.pojo.Team;
import com.iplanner.pojo.User;
import com.iplanner.service.UserServiceImpl;
import com.iplanner.utils.RandomRecommendation;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/initUser/{email}")
    @ResponseBody
    public User showUsername(@PathVariable("email") String email){
        User user = userService.queryUserByEmail(email);
        return user;
    }

    @GetMapping("/recommendation")
    public String recommendation(){
        return RandomRecommendation.getRandomRecommendation();
    }

    @GetMapping("/user/showSchedule/{id}")
    @ResponseBody
    public List<Schedule> showSchedule(@PathVariable("id") Long uid){
        List<Schedule> schedules = userService.queryScheduleByUserId(uid);
        return schedules;
    }

    @PostMapping("/user/addSchedule")
    @ResponseBody
    public void addScheduleByUser(@RequestBody JSONObject params){

        System.out.println(params);
        Long uid = Long.parseLong(params.get("uid").toString()) ;
        System.out.println(uid);

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
        userService.addScheduleByUser(uid, schedule);
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);

        String uid = jsonObject.get("uid").toString();

        long l = Long.parseLong(uid);

        System.out.println(l);

        return "ok";
    }

    @GetMapping("/user/showTeam/{id}")
    @ResponseBody
    public List<Team> showTeam(@PathVariable("id") Long uid){
        return userService.queryTeamByUserId(uid);
    }

    @GetMapping("/user/register/{email}/{pwd}")
    public String register(@PathVariable("email") String email,@PathVariable("pwd") String password){
        if(userService.queryUserByEmail(email) != null){
            return "existed";
        }else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername("iPlanner");
            Long uid = userService.insertUser(user);
            return uid.toString();
        }

    }

}
