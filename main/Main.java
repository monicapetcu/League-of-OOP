package main;

import java.io.FileNotFoundException;

import java.io.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            Scanner fileReader = new Scanner(file);
            int n, m, p, x, y, r, x1, x2, y1, y2;
            String player;
            char[] move;
            n = fileReader.nextInt();
            m = fileReader.nextInt();
            String[][] land = new String[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    land[i][j] = fileReader.next();
                }
            }
            p = fileReader.nextInt();
            Hero[] h = new Hero[p];
            for (int i = 0; i < p; i++) {
                player = fileReader.next();
                x = fileReader.nextInt();
                y = fileReader.nextInt();
                if (player.equals("P")) {
                    h[i] = new Pyromancer();
                    h[i].currentHPupdate(h[i].fullHP());
                }
                if (player.equals("R")) {
                    h[i] = new Rogue();
                    h[i].currentHPupdate(h[i].fullHP());
                }
                if (player.equals("K")) {
                    h[i] = new Knight();
                    h[i].currentHPupdate(h[i].fullHP());
                }
                if (player.equals("W")) {
                    h[i] = new Wizard();
                    h[i].currentHPupdate(h[i].fullHP());
                }
                h[i].location(x, y);
            }
            r = fileReader.nextInt();
            for (int i = 0; i < r; i++) {
                move = fileReader.next().toCharArray();
                for (int j = 0; j < p; j++) {
                    x = h[j].getx();
                    y = h[j].gety();
                    if (h[j].stsDoTK() == 0 && h[j].stsDoTR() == 0) {
                        if (move[j] == 'U') {
                            x--;
                            h[j].location(x, y);
                        }
                        if (move[j] == 'D') {
                            x++;
                            h[j].location(x, y);
                        }
                        if (move[j] == 'L') {
                            y--;
                            h[j].location(x, y);
                        }
                        if (move[j] == 'R') {
                            y++;
                            h[j].location(x, y);
                        }
                    }
                }
                // Aici se desfasoara lupta pentru fiecare runda
                for (int k = 0; k < p - 1; k++) {
                    for (int l = k + 1; l < p; l++) {
                        x1 = h[k].getx();
                        y1 = h[k].gety();
                        x2 = h[l].getx();
                        y2 = h[l].gety();
                        if (x1 == x2 && y1 == y2) {
                            if (h[k].deadsts() == 0 && h[l].deadsts() == 0) {
                                Duel d = new Duel(h[k], h[l], land[x1][y1]);
                                d.herosfight();
                            } else {
                                Duel d = new Duel(h[k], h[l], land[x1][y1]);
                                d.overtime();
                                if (h[k].currentHP() <= 0) {
                                    h[k].dead(1);
                                }
                                if (h[l].currentHP() <= 0) {
                                    h[l].dead(1);
                                }
                            }
                        }
                    }
                }
            }
            fileReader.close();
        // Scriem in fisier rezultatele
             File write = new File(args[1]);
             PrintWriter print = new PrintWriter(write);
             for (int i = 0; i < p; i++) {
                 if (h[i].deadsts() == 1) {
                     print.println(h[i].hero() + " dead");
                 } else {
                     print.println(h[i].hero() + " " + h[i].lvl() + " "
                 + (int) (h[i].xpsts()) + " " + (int) (h[i].currentHP()) + " "
                 + h[i].getx() + " " + h[i].gety());
                 }
             }
             print.close();

        } catch (FileNotFoundException ex) {
                System.err.println("File not found!");
                return;
           }
    }
}
