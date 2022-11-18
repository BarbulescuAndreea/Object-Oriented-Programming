package lab7.task1;

import lab7.task1.document.*;
import java.lang.*;

public class DokuWikiVisitor implements DocumentVisitor{
    private StringBuilder str = new StringBuilder();
    @Override
    public void visit(ItalicTextSegment italic){
        str.append("//").append(italic.getContent()).append("//");
    }

    @Override
    public void visit(BoldTextSegment bold) {
        str.append("**").append(bold.getContent()).append("**");
    }

    @Override
    public void visit(PlainTextSegment plain) {
        str.append(plain.getContent());
    }

    @Override
    public void visit(UrlSegment url) {
        str.append("[[").append(url.getContent()).append("|").
                append(url.getS()).append("]]");
    }

    public StringBuilder getDocument(){
        return str;
    }
}
