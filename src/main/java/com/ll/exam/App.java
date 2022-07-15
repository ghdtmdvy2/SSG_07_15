package com.ll.exam;

import java.util.Scanner;

public class App {
    Scanner sc;
    App(Scanner sc){
        this.sc = sc;
    }
    void run(){
        System.out.println("== 명언 SSG ==");
        System.out.print("명령)");
        String cmd = sc.nextLine();
        Rq rq = new Rq(cmd);
        outer:
        while(true){
            switch (rq.getPath()){
                case "종료":
                    break outer;
            }
        }
    }
}
