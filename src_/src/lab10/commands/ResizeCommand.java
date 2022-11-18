package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class ResizeCommand implements DrawCommand{
    private final DiagramComponent diagramComponent;
    public float resize;


    public ResizeCommand(DiagramCanvas canvas, int idx, float resize){
        this.diagramComponent = canvas.getComponent(idx);
        this.resize = resize;
    }


    @Override
    public void execute() {
          diagramComponent.setHeight((int)(diagramComponent.getHeight() * resize));
          diagramComponent.setWeight((int)(diagramComponent.getWeight() * resize));
    }

    @Override
    public void undo() {

        diagramComponent.setHeight((int)(diagramComponent.getHeight() / resize));
        diagramComponent.setWeight((int)(diagramComponent.getWeight() / resize));
    }
}
