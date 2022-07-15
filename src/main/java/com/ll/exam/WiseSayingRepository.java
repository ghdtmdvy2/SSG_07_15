package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingRepository {

    List<WiseSaying> wiseSayings;
    int wiseSayingid;

    public WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        wiseSayingid = 0;
    }

    public WiseSaying write(String content, String author) {
        wiseSayingid++;
        WiseSaying wiseSaying = new WiseSaying(wiseSayingid, content, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == id) {
                return wiseSaying;
            }
        }
        return null;
    }
    public boolean modify ( int id, String content, String author){
        WiseSaying wiseSaying = findById(id);
        if(wiseSaying == null){
            return false;
        }
        wiseSaying.content = content;
        wiseSaying.author = author;
        return true;
    }

    public boolean remove ( int id){
        for ( WiseSaying wiseSaying : wiseSayings){
            if ( wiseSaying.id == id ){
                wiseSayings.remove(wiseSaying);
                return true;
            }
        }
        return false;
    }
}
