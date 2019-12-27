package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        int rowUser;
        int columnUser;
        int gameOver = 0;
        int dificulty;
        int rows;
        int columns;
        int mineCounter = 0;
        String type;

        System.out.println("**************************************************************");
        System.out.println("Welcome to the Minesweeper game");
        System.out.println("-First you must choose the difficulty you want from 2.");
        System.out.println("-You will choose the row first and then the column");
        System.out.println("-Choose TYPE, if you think there is a mine:");
        System.out.println("Press (x) and if you're sure there won't be a mine, press (o)");
        System.out.println("Good luck!");
        System.out.println("**************************************************************");

        System.out.print("Nivel de dificultad: "); // We make the user enter the difficulty level
        dificulty = read.nextInt();
        System.out.println();

        String[][] arrayGeneretor = new String[dificulty][dificulty]; // create a variable where the matrix will be generated


        for (int i = 0; i < arrayGeneretor.length; i++) { //print the matrix here
            for (int j = 0; j < arrayGeneretor[i].length; j++) {
                arrayGeneretor[i][j] = " - ";
                System.out.print(arrayGeneretor[i][j]);
            }
            System.out.println();
        }

        int[][] arrayOfMines = new int[dificulty][dificulty]; // create a variable that will be to generate mines

        for (int i = 0; i < dificulty; i++) { // This loop will be used to generate random mines
            rows = (int) (Math.random() * dificulty);
            columns = (int) (Math.random() * dificulty);
            if (arrayOfMines[rows][columns] != 9) {
                arrayOfMines[rows][columns] = 9;
            } else {
                i--;
            }
        }

        // here we check if there are mines around or not
        for (int i = 0; i < dificulty; i++) {
            for (int j = 0; j < dificulty; j++) {
                if (arrayOfMines[i][j] != 9) {
                    if (i != dificulty - 1) { // we check all rows except the last one
                        if (j - 1 >= 0) { // we check down on the left
                            if (arrayOfMines[i + 1][j - 1] == 9) {
                                arrayOfMines[i][j]++;
                            }
                        }
                        if (j + 1 <= dificulty - 1) { // we check bottom right
                            if (arrayOfMines[i + 1][j + 1] == 9) {
                                arrayOfMines[i][j]++;
                            }
                        }
                        if (arrayOfMines[i + 1][j] == 9) { // check below
                            arrayOfMines[i][j]++;
                        }
                    }

                    if (i != 0) { // we check all rows except the first
                        if (j - 1 >= 0) {// we bought top left
                            if (arrayOfMines[i - 1][j - 1] == 9) {
                                arrayOfMines[i][j]++;
                            }
                        }
                        if (j + 1 <= dificulty - 1) {// we check up on the right
                            if (arrayOfMines[i - 1][j + 1] == 9) {
                                arrayOfMines[i][j]++;
                            }
                        }
                        if (arrayOfMines[i - 1][j] == 9) { // check above
                            arrayOfMines[i][j]++;
                        }
                    }

                    if (j - 1 >= 0) {
                        if (arrayOfMines[i][j - 1] == 9) { // check left
                            arrayOfMines[i][j]++;
                        }
                    }

                    if (j + 1 <= dificulty - 1) {
                        if (arrayOfMines[i][j + 1] == 9) { // check right
                            arrayOfMines[i][j]++;
                        }
                    }
                }
            }
        }

        // In this loop serves to start the game
        while (gameOver < 1) {
            System.out.print("Introduzca una fila: ");
            rowUser = read.nextInt(); // The user enters a row

            for (int i = 0; i < 1; ) { // in this loop is an error control if the user incorrectly enters the value
                if (rowUser > dificulty || rowUser < 0) {
                    System.out.print("Introduzca una fila: ");
                    rowUser = read.nextInt();
                } else {
                    i++;
                }
            }

            System.out.print("Introduzca una columna: ");
            columnUser = read.nextInt(); // The user enters a column

            for (int i = 0; i < 1; ) { // in this loop is an error control if the user incorrectly enters the value
                if (columnUser > dificulty || columnUser < 0) {
                    System.out.print("Introduzca una columna: ");
                    columnUser = read.nextInt();
                } else {
                    i++;
                }
            }

            System.out.print("Tipo: ");
            type = read.next(); // the user enters a type: "o", "x".

            System.out.println();

            // Switch for type if it is (x) or (o)
            switch (type) {
                case "x": // ask the user if there is a mine or not
                    if (arrayOfMines[rowUser - 1][columnUser - 1] == 9) {
                        arrayGeneretor[rowUser - 1][columnUser - 1] = " ? ";
                        mineCounter++;
                    } else {
                        arrayGeneretor[rowUser - 1][columnUser - 1] = " ? ";
                    }
                    break;
                case "o": // ask the user if they are sure there is nothing
                    if (arrayOfMines[rowUser - 1][columnUser - 1] >= 9) { // if " o " is equal to or greater than 9 = "Game over"
                        arrayGeneretor[rowUser - 1][columnUser - 1] = " x ";
                        System.err.println(" Game Over");
                        gameOver++;
                    } else if (arrayOfMines[rowUser - 1][columnUser - 1] < 9) {
                        arrayGeneretor[rowUser - 1][columnUser - 1] = " " + (arrayOfMines[rowUser - 1][columnUser - 1] + " ");
                    }
                    break;
            }

            // here we indicate that if the counter is the same as the difficulty, that means that there are no more mines and the game is over
            if (mineCounter == dificulty) {
                System.err.println("Fin de la partida");
                gameOver++;
            }

            // Print the board again
            for (String[] strings : arrayGeneretor) {
                for (String string : strings) {
                    System.out.print(string);
                }
                System.out.println();
            }
        }
    }
}
