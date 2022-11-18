package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class ChangeColorCommand implements DrawCommand{
    private final DiagramComponent diagramComponent;
    private String previousColor;
    private final String nextColor;

    public ChangeColorCommand(int idx, DiagramCanvas canvas, String color){
        this.diagramComponent = canvas.getComponent(idx);
        nextColor = color;
    }

    @Override
    public void execute() {
        previousColor = diagramComponent.getColor();
        diagramComponent.setColor(nextColor);
    }

    @Override
    public String toString() {
        return "ChangeColorCommand{"  +
                "diagramComponent=" + diagramComponent +
                ", previousColor='" + previousColor + '\'' +
                ", nextColor='" + nextColor + '\'' +
                '}';
    }

    @Override
    public void undo() {
        diagramComponent.setColor(previousColor);
    }
}
