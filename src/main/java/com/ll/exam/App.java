package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc;
    WiseSayingController wiseSayingController;
    App(Scanner sc){
        this.sc = sc;
        wiseSayingController = new WiseSayingController(sc);
    }
    void run(){
        String content;
        String author;
        int paramId;
        outer:
        while(true){;
            System.out.println("== 명언 SSG ==");
            System.out.print("명령)");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()){
                case "등록":
                    wiseSayingController.write(rq);
                    break;
                case "목록":
                    wiseSayingController.list(rq);
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "수정":
                    wiseSayingController.modify(rq);
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
