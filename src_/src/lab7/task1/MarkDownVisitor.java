package lab7.task1;

import lab7.task1.document.*;

public class MarkDownVisitor implements DocumentVisitor {
        private StringBuilder str = new StringBuilder();

        public void visit(ItalicTextSegment italic){
                str.append("*").append(italic.getContent()).append("*");
        }
        public void visit(BoldTextSegment bold){
                str.append("**").append(bold.getContent()).append("**");
        }
        public void visit(PlainTextSegment plain){
                str.append(plain.getContent());
        }
        public void visit(UrlSegment url){
                str.append("[").append(url.getContent()).append("]").append("(")
                        .append(url.getS()).append(")");
        }
        public StringBuilder getDocument(){
                return str;
        }
}
