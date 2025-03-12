package com.project.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n-- WELCOME TO THE GRAPH GENERATOR! --\n");

        GraphGenerator generator = new GraphGenerator();

        String charSequence;

        Scanner sc = new Scanner(System.in);
        System.out.println("Type a char sequence here: ");
        charSequence = sc.nextLine();
        generator.addCharSequence(charSequence);

        System.out.println("\n>> GENERATED MATRIX <<\n\n" + generator.getMatrix());

    }
}