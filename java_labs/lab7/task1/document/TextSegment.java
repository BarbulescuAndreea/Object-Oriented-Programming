package lab7.task1.document;


import lab7.task1.DocumentVisitor;
import lab7.task1.Visitable;

/**
 * Represents a text segment of a document that needs to be parsed.
 */
public abstract class TextSegment implements Visitable {
    private String content;

    TextSegment(String content) {
        this.content = content;
    }

    public TextSegment() {}

    public String getContent() {
        return content;
    }

    // TODO add method for applying the visitor

     public void accept(DocumentVisitor v){
    }
}
