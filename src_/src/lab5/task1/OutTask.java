package lab5.task1;

public class OutTask implements Task {
    public String string;

    public OutTask(String string){
        this.string = string;
    }
    public void execute(){

        System.out.println(string);
    }
    @Override
    public String toString() {
        return "string= " + string;
    }
}
