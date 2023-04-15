package com.iplanner.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomRecommendation {

    public static String getRandomRecommendation(){
        List<String> recommendations = new LinkedList<>();
        recommendations.add("早出晚归工作忙,注意防凉添衣裳");
        recommendations.add("瑞气祥云,今为君开");
        recommendations.add("回家记得洗个手哦");
        recommendations.add("再忙也别忘记吃早饭哦");
        Random random = new Random();
        int i = random.nextInt(4);
        return recommendations.get(i);
    }
}
