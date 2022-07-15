package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    Scanner sc;

    WiseSayingService wiseSayingService;
    WiseSayingController(Scanner sc){
        wiseSayingService = new WiseSayingService();
        this.sc = sc;
    }
    public void write(Rq rq) {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();
        WiseSaying wiseSaying = wiseSayingService.write(content,author);
        System.out.printf("%d번 명언이 등록되었습니다.\n",wiseSaying.id);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> wiseSayings = wiseSayingService.findAll();
        for ( WiseSaying wiseSaying : wiseSayings){
            System.out.printf("%d / %s / %s\n",wiseSaying.id,wiseSaying.author,wiseSaying.content);
        }
    }

    public void remove(Rq rq) {
        int paramId = rq.getIntParam("id",0);
        if (wiseSayingService.remove(paramId)) {
            System.out.printf("%d번 명언이 삭제되었습니다.\n",paramId);
            return;
        }
        System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
        return;
    }

    public void modify(Rq rq) {
        int paramId = rq.getIntParam("id",0);
        WiseSaying wiseSaying = wiseSayingService.findById(paramId);
        if(wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
            return;
        }
        System.out.printf("명언(기존) : %s",wiseSaying.content);
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가(기존) : %s",wiseSaying.author);
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();
        wiseSayingService.modify(paramId,content,author);
        }
}

