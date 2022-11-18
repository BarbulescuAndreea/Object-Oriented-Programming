package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class DrawRectangleCommand implements DrawCommand{

    private DiagramComponent d1 = new DiagramComponent();
    private DiagramCanvas canvas;

    public DrawRectangleCommand(DiagramCanvas canvas){
        this.canvas = canvas;
    }

    @Override
    public String toString() {
        return "DrawRectangle{" +
                "d1=" + d1 +
                '}';
    }

    @Override
    public void execute() {
        canvas.addComponent(d1);
    }


    public void undo() {
        canvas.removeComponent(d1);
    }
}
