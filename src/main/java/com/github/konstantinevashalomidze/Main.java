package com.github.konstantinevashalomidze;

import com.github.konstantinevashalomidze.humanformatter.DurationFormatter;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        System.out.println(DurationFormatter.format(Duration.ofMillis(1500)));
        System.out.println(DurationFormatter.format(Duration.ofMillis(-1500)));
    }
}