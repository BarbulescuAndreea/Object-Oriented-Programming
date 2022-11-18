package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

import java.util.ArrayList;
import java.util.List;

public class ConnectComponentsCommand implements DrawCommand{
    private DiagramCanvas canvas;
    private DiagramCanvas canvas2;
    private final int idx1;
    private final int idx2;
    public ConnectComponentsCommand(int idx1, int idx2, DiagramCanvas canvas, DiagramCanvas canvas2){
        this.idx1 = idx1;
        this.idx2 = idx2;
        this.canvas = canvas;
        this.canvas2 = canvas2;
    }
    @Override
    public void execute() {

        //conectează o DiagramComponent la alta
        canvas.getComponent(idx1).connectTo(Integer.toString(idx2));
    }

    @Override
    public void undo() {
        canvas.getComponent(idx1).removeConnection(Integer.toString(idx2));
    }
}
