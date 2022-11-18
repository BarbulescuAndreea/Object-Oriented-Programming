package lab7.task1;

import lab7.task1.document.*;

import java.util.List;
import lab7.task1.DocumentVisitor;

/**
 * Uses visitors to parse documents and provide dokuwiki and markdown outputs.
 */
public class WikiGenerator {
    private final List<TextSegment> textSegments;
    public WikiGenerator(List<TextSegment> textSegments) {
        this.textSegments = textSegments;
    }

    public StringBuilder getDokuWikiDocument() {
        // TODO apply DokuWiki visitor on the text segments
        StringBuilder str = new StringBuilder();
        for(TextSegment e : textSegments){
            DokuWikiVisitor d = new DokuWikiVisitor();
            e.accept(d);
            str.append(d.getDocument());
        }
        return str;
    }

    public StringBuilder getMarkdownDocument() {
        // TODO apply Markdown visitor on the text segments
        StringBuilder str = new StringBuilder();
        for (TextSegment e : textSegments) {
            MarkDownVisitor m = new MarkDownVisitor();
            e.accept(m);
            str.append(m.getDocument());
        }
        return str;
    }
}
