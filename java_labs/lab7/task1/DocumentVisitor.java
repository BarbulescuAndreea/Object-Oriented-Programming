package lab7.task1;

import lab7.task1.document.*;

public interface DocumentVisitor{
    void visit(ItalicTextSegment italic);
    void visit(BoldTextSegment bold);
    void visit(PlainTextSegment plain);
    void visit(UrlSegment url);
}
