package com.nostratech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private JFrame frame;
    private JButton[][] boardButtons;
    private String[][] board ;
    private String selectedPiece = null;
    private int selectedRow = -1, selectedCol = -1;


    public Gui() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(8, 8));
        boardButtons = new JButton[8][8];
        board = new String[8][8];

        initializeBoardUI();
        frame.setVisible(true);
    }

    private void initializeBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.setBackground((row + col) % 2 == 0 ? new Color(245, 222, 179) : new Color(184, 134, 11));
                int finalRow = row;
                int finalCol = col;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(finalRow, finalCol);
                    }
                });
                boardButtons[row][col] = button;
                frame.add(button);
            }
        }
        setupPieces();
        updateBoardUI();
    }

    private void setupPieces() {
        String[] backRow = {"R", "N", "B", "Q", "K", "B", "N", "R"};
        for (int col = 0; col < 8; col++) {
            board[0][col] = "B" + backRow[col];
            board[1][col] = "BP";
            board[6][col] = "WP";
            board[7][col] = "W" + backRow[col];
        }
    }


       private void updateBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String piece = board[row][col];
                boardButtons[row][col].setText(piece != null ? piece : "");
            }
        }
    }
    private void handleButtonClick(int row, int col) {
        if (selectedPiece == null && board[row][col] != null) {
            selectedPiece = board[row][col];
            selectedRow = row;
            selectedCol = col;
        } else if (selectedPiece != null) {
            board[selectedRow][selectedCol] = null;
            board[row][col] = selectedPiece;
            selectedPiece = null;
            updateBoardUI();
        }
    }

}
