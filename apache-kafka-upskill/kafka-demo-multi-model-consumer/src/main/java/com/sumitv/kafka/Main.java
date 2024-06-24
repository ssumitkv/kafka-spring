package com.sumitv.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        new ArrayList<Integer>( Arrays.asList(new Integer[]{1, 2});
//        SpringApplication.run(Main.class, args);

        int[] a = {135,101,170,125,79,159,163,65,106,146,82,28,162,92,196,143,28,37,192,5,103,154,93,183,22,117,119,96,48,127,172,139,70,113,68,100,36,95,104,12,123,134};
        int sum = 468;
        int n = 42;

        int[] temp = {95,104,12,123,134};

//        System.out.println( Arrays.stream(temp).sum());

        System.out.println(subarraySum(a, n, sum));

    }




}