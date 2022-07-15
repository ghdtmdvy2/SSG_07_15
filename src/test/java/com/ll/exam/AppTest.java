package com.ll.exam;

import com.ll.exam.App;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    @Test
    void 파일에_있는_JSON을_읽어서_맵으로_변환() {
        Util.file.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1,"명언1","작가1");
        Util.file.saveToFile("test_data/1.json", wiseSaying.toString());
        Map<String, Object> map = Util.json.jsonToMapFromFile("test_data/1.json");
        assertEquals(1,map.get("id"));
        assertEquals("명언1",map.get("content"));
        assertEquals("작가1",map.get("author"));
    }
    @Test
    void 객체를_파일로_저장() {
        Util.file.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1,"명언1","작가1");
        Util.file.saveToFile("test_data/1.json", wiseSaying.toString());
        String rs = Util.file.readFromFile("test_data/1.json","");
        assertEquals(rs,wiseSaying.toString());
    }
    @Test
    void 파일에_내용쓰기() {
        Util.file.mkdir("test_data");
        Util.file.saveToFile("test_data/1.json", "내용");
    }
    @Test
    public void 프로그램_시작시_타이틀_출력_그리고_종료() {
        Scanner sc = TestUtil.genScanner("종료");
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("== 명언 SSG =="));
        assertTrue(rs.contains("명령)"));
    }
    @Test
    public void 프로그램_시작시_타이틀_등록_그리고_종료() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("명언 : "));
        assertTrue(rs.contains("작가 : "));
    }
    @Test
    public void 등록시_생성된_명언번호_노출() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 명언이 등록되었습니다."));
    }
    @Test
    public void 등록할때마다_생성되는_명언번호가_증가() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                등록
                명언2
                작가2
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 명언이 등록되었습니다."));
        assertTrue(rs.contains("2번 명언이 등록되었습니다."));
    }
    @Test
    public void 등록후_목록() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                등록
                명언2
                작가2
                목록
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("번호 / 작가 / 명언"));
        assertTrue(rs.contains("----------------------"));
        assertTrue(rs.contains("2 / 작가2 / 명언2"));
        assertTrue(rs.contains("1 / 작가1 / 명언1"));
    }
    @Test
    public void 등록후_삭제() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                등록
                명언2
                작가2
                목록
                삭제?id=1
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        assertTrue(rs.contains("1번 명언이 삭제되었습니다."));
    }
    @Test
    public void 등록후_삭제_예외처리() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                등록
                명언2
                작가2
                목록
                삭제?id=1
                삭제?id=1
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 명언은 존재하지 않습니다."));
    }
    @Test
    public void 등록후_삭제_수정() {
        Scanner sc = TestUtil.genScanner("""
                등록
                명언1
                작가1
                등록
                명언2
                작가2
                목록
                삭제?id=1
                삭제?id=1
                수정?id=2
                현재와 자신을 사랑하라.
                홍길동                
                목록
                종료
                """);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        assertTrue(rs.contains("명언(기존) :"));
        assertTrue(rs.contains("명언 :"));
        assertTrue(rs.contains("작가(기존) :"));
        assertTrue(rs.contains("작가 :"));
        assertTrue(rs.contains("2 / 홍길동 / 현재와 자신을 사랑하라."));
    }
}