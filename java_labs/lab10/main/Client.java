package lab10.main;

import lab10.commands.*;
import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

/**
 * Receives commands in clear text from the user and transforms them in DrawCommand objects. It uses the Invoker to
 * execute the given commands.
 */
public class Client {

    private Invoker invoker;
    private DiagramCanvas diagramCanvas;

    Client() {
        invoker = new Invoker();
        diagramCanvas = new DiagramCanvas();
    }

    public void showDiagram() {
        System.out.println(diagramCanvas);
        // TODO
    }

    public void newDiagram() {
        diagramCanvas = new DiagramCanvas();
        // TODO
    }

    public void executeAction(String commandName, String ...args) {
        // TODO - uncomment:
        DrawCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType, args);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }

        // TODO - Execute the action
        invoker.execute(command);

    }

    private DrawCommand getCommand(CommandType type, String ...args) throws IllegalArgumentException {
        // TODO factory method to create DrawCommand subclasses.
        if(type.text.equals("change color")){
            return new ChangeColorCommand(Integer.parseInt(args[0]),diagramCanvas, args[1]);
        }
        if(type.text.equals("change text")){
            return new ChangeTextCommand(diagramCanvas, Integer.parseInt(args[0]), args[1]);
        }
        if(type.text.equals("resize")){
            return new ResizeCommand(diagramCanvas,Integer.parseInt(args[0]), Float.parseFloat(args[1]));
        }
        if(type.text.equals("draw rectangle")){
            return new DrawRectangleCommand(diagramCanvas);
        }
        if(type.text.equals("connect")){
            return new ConnectComponentsCommand(Integer.parseInt(args[0]), Integer.parseInt(args[1]), diagramCanvas, diagramCanvas);
        }
        // If there is an exception when parsing the string arguments (NumberFormatException) catch it and
        // throw an IllegalArgumentException

        return null;
    }

    public void undo(){
        invoker.undo();

        //
    }

    public void redo() {
        invoker.redo();
        // TODO
    }
}
