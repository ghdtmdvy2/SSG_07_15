package com.ll.exam;

import java.util.Scanner;

public class App {
    Scanner sc;
    App(Scanner sc){
        this.sc = sc;
    }
    void run(){
        outer:
        while(true){
            System.out.println("== 명언 SSG ==");
            System.out.print("명령)");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()){
                case "등록":
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String author = sc.nextLine().trim();
                    System.out.println("1번 명언이 등록되었습니다.");
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
