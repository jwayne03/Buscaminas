package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        int filaUsuario;
        int columnaUsuario;
        int gameOver = 0;
        int dificultad;
        int filas;
        int columnas;
        String tipo;
        int contadorMinas = 0;

        System.out.print("Nivel de dificultad: "); //Hacemos que el usuario introduzca el nivel de dificultad
        dificultad = read.nextInt();
        System.out.println();

        String[][] matrizGenerada = new String[dificultad][dificultad]; //creamos una variable donde se generará la matriz

        for (int i = 0; i < matrizGenerada.length; i++) { //aqui imprime la matriz
            for (int j = 0; j < matrizGenerada[i].length; j++) {
                matrizGenerada[i][j] = " - ";
                System.out.print(matrizGenerada[i][j]);
            }
            System.out.println();
        }


        int[][] matrizMinas = new int[dificultad][dificultad]; //creamos una variable que será para generar minas

        for (int i = 0; i < dificultad; i++) { //Este bucle se usará para generar minas de forma aleatorias
            filas = (int) (Math.random() * dificultad);
            columnas = (int) (Math.random() * dificultad);
            if (matrizMinas[filas][columnas] != 9) {
                matrizMinas[filas][columnas] = 9;
            } else {
                i--;
            }
        }

        //aqui comprobamos si alrededor hay minas o no
        for (int i = 0; i < dificultad; i++) {
            for (int j = 0; j < dificultad; j++) {
                if (matrizMinas[i][j] != 9) {

                    if (i != dificultad - 1) { //comprobamos todas las filas menos la ultima
                        if (j - 1 >= 0) { //comprobamos abajo a la izquierda
                            if (matrizMinas[i + 1][j - 1] == 9) {
                                matrizMinas[i][j]++;
                            }
                        }
                        if (j + 1 <= dificultad - 1) { //comprobamos abajo a la derecha
                            if (matrizMinas[i + 1][j + 1] == 9) {
                                matrizMinas[i][j]++;
                            }
                        }
                        if (matrizMinas[i + 1][j] == 9) { //comprobar abajo
                            matrizMinas[i][j]++;
                        }
                    }

                    if (i != 0) { //comprobamos todas las filas menos la primera
                        if (j - 1 >= 0) { //comprabamos arriba a la izquierda
                            if (matrizMinas[i - 1][j - 1] == 9) {
                                matrizMinas[i][j]++;
                            }
                        }
                        if (j + 1 <= dificultad - 1) { //comprobamos arriba a la derecha
                            if (matrizMinas[i - 1][j + 1] == 9) {
                                matrizMinas[i][j]++;
                            }
                        }
                        if (matrizMinas[i - 1][j] == 9) { //comprobar arriba
                            matrizMinas[i][j]++;
                        }
                    }

                    if (j - 1 >= 0) {
                        if (matrizMinas[i][j - 1] == 9) { //comprobar izquierda
                            matrizMinas[i][j]++;
                        }
                    }

                    if (j + 1 <= dificultad - 1) {
                        if (matrizMinas[i][j + 1] == 9) { //comprobar derecha
                            matrizMinas[i][j]++;
                        }
                    }
                }
            }
        }

        //En este bucle sirve para iniciar el juego
        while (gameOver < 1) {
            System.out.print("Introduzca una fila: ");
            filaUsuario = read.nextInt(); //El usuario introduce una fila

            for (int i = 0; i < 1; ) { //en este bucle es un control de errores por si el usuario introduce de forma incorrecta el valor
                if (filaUsuario > dificultad || filaUsuario < 0) {
                    System.out.print("Introduzca una fila: ");
                    filaUsuario = read.nextInt();
                } else {
                    i++;
                }
            }

            System.out.print("Introduzca una columna: ");
            columnaUsuario = read.nextInt(); //El usuario introduce una columna

            for (int i = 0; i < 1; ) { //en este bucle es un control de errores por si el usuario introduce de forma incorrecta el valor
                if (columnaUsuario > dificultad || columnaUsuario < 0) {
                    System.out.print("Introduzca una columna: ");
                    columnaUsuario = read.nextInt();
                } else {
                    i++;
                }
            }

            System.out.print("Tipo: ");
            tipo = read.next(); //el usuario introduce un tipo: "o" , "x".

            //Switch para el tipo
            switch (tipo) {
                case "x": //preguntar al usuario si hay una mina o no
                    if (matrizMinas[filaUsuario - 1][columnaUsuario - 1] == 9) {
                        matrizGenerada[filaUsuario - 1][columnaUsuario - 1] = " ? ";
                        contadorMinas++;
                    } else {
                        matrizGenerada[filaUsuario - 1][columnaUsuario - 1] = " ? ";
                    }
                    break;
                case "o": //preguntar al usuario si esta seguro que no hay nada
                    if (matrizMinas[filaUsuario - 1][columnaUsuario - 1] >= 9) { //si "o" es igual o mayor que 9 = "Game over"
                        System.err.println("Game Over");
                        matrizGenerada[filaUsuario - 1][columnaUsuario - 1] = " x ";
                        gameOver++;
                    } else if (matrizMinas[filaUsuario - 1][columnaUsuario - 1] < 9) {
                        matrizGenerada[filaUsuario - 1][columnaUsuario - 1] = " " + String.valueOf(matrizMinas[filaUsuario - 1][columnaUsuario - 1] + " ");
                    }
                    break;
            }

            //aqui indicamos que si el contador es igual que la dificultad, eso significa que ya no hay mas minas y el juego ha terminado
            if (contadorMinas == dificultad) {
                System.err.println("Fin de la partida");
                gameOver++;
            }

            //Imprimir el tablero otra vez
            for (int i = 0; i < matrizGenerada.length; i++) {
                for (int j = 0; j < matrizGenerada[i].length; j++) {
                    System.out.print(matrizGenerada[i][j]);
                }
                System.out.println();
            }
        }
    }
}