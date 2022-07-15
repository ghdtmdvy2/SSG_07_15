package com.ll.exam;

public class WiseSaying {
    int id;
    String content;
    String author;
    WiseSaying(int id, String content, String author){
        this.id = id;
        this.content = content;
        this.author = author;
    }
    @Override
    public String toString(){
        return """
                {
                    "id" : %d,
                    "content" : "%s",
                    "author" : "%s"
                }
                """.stripIndent().formatted(this.id,this.content,this.author).trim();
    }
}
