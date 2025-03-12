package com.project.main;

import javax.swing.*;
import java.util.Objects;
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

        try {
            System.out.println("Generating visual graph...");
            Thread.sleep(3000);
            SwingUtilities.invokeLater(() ->
                    new GraphFrame(generator.getEdges(), generator.getUniqueVertices()).setVisible(true)
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n-- THANK YOU FOR USING THE GRAPH GENERATOR :D --\n");

        System.out.println("Daniel Simões Fiaccadori Borborema");
        System.out.println("Kaique Freire dos Santos");

    }
}