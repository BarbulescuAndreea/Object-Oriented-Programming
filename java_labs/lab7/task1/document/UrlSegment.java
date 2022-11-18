package lab7.task1.document;

import lab7.task1.DocumentVisitor;


public class UrlSegment extends TextSegment{

    private String content;
    private String s;

    public UrlSegment(String content) {
        super(content);
    }

    public UrlSegment(String s, String content) {
        super();
        this.s = s;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
    public String getS() {
        return s;
    }

    @Override
    public void accept(DocumentVisitor v) {
        v.visit(this);
    }
}
