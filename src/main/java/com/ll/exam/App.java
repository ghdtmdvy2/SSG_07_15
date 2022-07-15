package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc;
    List<WiseSaying> wiseSayings = new ArrayList<>();
    int wiseSayingid;
    App(Scanner sc){
        this.sc = sc;
        wiseSayingid = 0;
    }
    void run(){
        String content;
        String author;
        int paramId;
        outer:
        while(true){
            System.out.println("== 명언 SSG ==");
            System.out.print("명령)");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()){
                case "등록":
                    System.out.print("명언 : ");
                    content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    author = sc.nextLine().trim();
                    wiseSayingid++;
                    WiseSaying wiseSaying = new WiseSaying(wiseSayingid,content,author);
                    wiseSayings.add(wiseSaying);
                    System.out.printf("%d번 명언이 등록되었습니다.\n",wiseSayingid);
                    break;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    for ( WiseSaying wiseSaying_ : wiseSayings){
                        System.out.printf("%d / %s / %s\n",wiseSaying_.id,wiseSaying_.author,wiseSaying_.content);
                    }
                    break;
                case "삭제":
                    paramId = rq.getIntParam("id",0);
                    for ( WiseSaying wiseSaying_ : wiseSayings){
                        if ( wiseSaying_.id == paramId ){
                            wiseSayings.remove(wiseSaying_);
                            System.out.printf("%d번 명언이 삭제되었습니다.\n",paramId);
                            continue;
                        }
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
                    }
                    break;
                case "수정":
                    paramId = rq.getIntParam("id",0);
                    for ( WiseSaying wiseSaying_ : wiseSayings){
                        if ( wiseSaying_.id == paramId ){
                            System.out.printf("명언(기존) : %s",wiseSaying_.content);
                            System.out.print("명언 : ");
                            content = sc.nextLine().trim();
                            System.out.printf("작가(기존) : %s",wiseSaying_.author);
                            System.out.print("작가 : ");
                            author = sc.nextLine().trim();
                            wiseSaying_.content = content;
                            wiseSaying_.author = author;
                            continue;
                        }
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
                    }
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
