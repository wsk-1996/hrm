package com.yueqian.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int x=100;

    public static void main(String arg[]) {
        Scanner input = new Scanner(System.in);
        /*int m1=input.nextInt();
        int m2=input.nextInt();
        int m3=input.nextInt();*/
        new ArrayList<>();
        int n = 6;
        int c=0;
        for (int i = 1; i <=n ; i++) {
            c=c+i;
            if(c>=n){
                System.out.println(i);
                break;
            }
        }
    }
}
