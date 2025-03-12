package com.project.main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphFrame extends JFrame {
    private List<int[]> edges;
    private Set<Integer> vertices;

    public GraphFrame(List<int[]> edges, Set<Integer> vertices) {
        this.edges = edges;
        this.vertices = vertices;

        setTitle("-- GRAPH VIEW --");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GraphPanel());
    }

    private class GraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGraph(g);
        }

        private void drawGraph(Graphics g) {
            int radius = 25;
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int numNodes = vertices.size();
            int centerX = panelWidth / 2;
            int centerY = panelHeight / 2;
            int circleSize = Math.min(panelWidth, panelHeight) / 3;

            int[][] positions = new int[numNodes][2];
            int index = 0;
            Integer[] vertexArray = vertices.toArray(new Integer[0]);

            for (int i = 0; i < numNodes; i++) {
                double angle = 2 * Math.PI * i / numNodes;
                positions[index][0] = centerX + (int) (circleSize * Math.cos(angle));
                positions[index][1] = centerY + (int) (circleSize * Math.sin(angle));
                index++;
            }

            Map<Integer, Integer> vertexToIndex = new HashMap<>();
            for (int i = 0; i < vertexArray.length; i++) {
                vertexToIndex.put(vertexArray[i], i);
            }

            g.setColor(Color.BLACK);
            for (int[] edge : edges) {
                if (vertexToIndex.containsKey(edge[0]) && vertexToIndex.containsKey(edge[1])) {
                    int v1 = vertexToIndex.get(edge[0]);
                    int v2 = vertexToIndex.get(edge[1]);
                    g.drawLine(positions[v1][0], positions[v1][1], positions[v2][0], positions[v2][1]);
                }
            }

            g.setColor(Color.BLUE);
            for (int i = 0; i < numNodes; i++) {
                int x = positions[i][0] - radius / 2;
                int y = positions[i][1] - radius / 2;
                g.fillOval(x, y, radius, radius);
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(vertexArray[i]), x + 6, y + 14);
                g.setColor(Color.BLUE);
            }
        }

    }
}
