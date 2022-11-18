package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class ChangeTextCommand implements DrawCommand {
    private final DiagramComponent diagramComponent;
    private String previousText;
    private String nextText;

    public ChangeTextCommand(DiagramCanvas canvas, int idx,  String text){
            this.diagramComponent = canvas.getComponent(idx);
            nextText = text;
        }

        @Override
        public void execute() {
            previousText = diagramComponent.getText();
            diagramComponent.setText(nextText);
        }

    @Override
    public void undo() {
        diagramComponent.setText(previousText);

    }
}
