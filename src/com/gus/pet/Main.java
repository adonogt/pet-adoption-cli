package com.gus.pet;

import com.gus.pet.menu.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String now = dateTime.format(formatter);
        String name = "Sunny Braccio";
        StringBuilder sb = new StringBuilder();
        sb.append(now).append("-").append(name.replace(" ","").toUpperCase());
        System.out.println(sb);
//        Scanner input = new Scanner(System.in);
//        Menu menu = new Menu(input);
//        while(!menu.printOptions());
    }
}
