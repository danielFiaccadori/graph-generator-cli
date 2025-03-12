package com.project.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphGenerator {

    private static final String GRAY = "\u001B[90m";   // Gray color
    private static final String GREEN = "\u001B[32m";  // Green color
    private static final String BOLD = "\u001B[1m";    // Bold
    private static final String RESET = "\u001B[0m";   // Resets color/style

    private List<int[]> edges = new ArrayList<>();
    private Set<Integer> uniqueVertices = new HashSet<>();

    private String charSequence;

    // >> MAIN METHODS <<

    public List<int[]> getEdges() {
        return edges;
    }

    public Set<Integer> getUniqueVertices() {
        return uniqueVertices;
    }

    public GraphGenerator() {
    }

    public String getCharSequence() {
        return charSequence;
    }

    public void addCharSequence(String charSequence) {
        this.charSequence = charSequence;
        parseEdges();
    }


    public String getMatrix() {
        return buildMatrix();
    }

    // >> UTILITY METHODS <<

    private void parseEdges() {
        edges.clear();
        uniqueVertices.clear();

        if (charSequence == null || charSequence.isEmpty()) {
            return;
        }

        String[] pairs = charSequence.split(", ");
        for (String pair : pairs) {
            String[] vertex = pair.split(" ");
            int v1 = Integer.parseInt(vertex[0]);
            int v2 = Integer.parseInt(vertex[1]);
            edges.add(new int[]{v1, v2});
            uniqueVertices.add(v1);
            uniqueVertices.add(v2);
        }
    }

    private String buildMatrix() {

        if (charSequence == null || charSequence.isEmpty()) {
            return "No char sequence provided!";
        }

        int maxVertex = 0;

        // Divides the String with spaces
        String[] charSequenceArray = charSequence.split(", ");
        for (String pair : charSequenceArray) {
            String[] vertex = pair.split(" ");
            int char1 = Integer.parseInt(vertex[0]);
            int char2 = Integer.parseInt(vertex[1]);
            maxVertex = Math.max(maxVertex, Math.max(char1, char2));
        }

        int[][] matrix = new int[maxVertex][maxVertex];

        for (String pair : charSequenceArray) {
            String[] vertex = pair.split(" ");
            int char1 = Integer.parseInt(vertex[0]) - 1;
            int char2 = Integer.parseInt(vertex[1]) - 1;
            matrix[char1][char2] = 1;
            matrix[char2][char1] = 1;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(BOLD).append(GREEN).append("■ ").append(RESET);                    // <-- "■" represents the vertexes (or A/V)
        for (int i = 0; i < maxVertex; i++) {
            sb.append(BOLD).append(GRAY).append(i + 1).append(" ").append(RESET);
        }
        sb.append("\n");

        for (int i = 0; i < maxVertex; i++) {
            sb.append(BOLD).append(GRAY).append(i + 1).append(" ").append(RESET);   // <-- sided indexes
            for (int j = 0; j < maxVertex; j++) {
                sb.append(matrix[i][j]).append(" ");                                // <-- fills the matrix
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
