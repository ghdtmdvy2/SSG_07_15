package com.ll.exam;

import java.util.List;
import java.util.Scanner;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;
    WiseSayingService(){
        wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.write(content, author);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public boolean modify(int id, String content, String author) {
        return wiseSayingRepository.modify(id, content, author);
    }

    public boolean remove(int id) {
        return wiseSayingRepository.remove(id);
    }
}