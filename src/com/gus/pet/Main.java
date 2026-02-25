package com.gus.pet;

import com.gus.pet.menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu(input);
        while(!menu.printOptions());
    }
}
