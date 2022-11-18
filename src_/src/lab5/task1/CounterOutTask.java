package lab5.task1;

public class CounterOutTask implements Task {
    int contor = 0;
    @Override
    public String toString() {
        return "contor= " + contor;
    }
    public CounterOutTask(){
       contor++;
    }
    public void execute(){

        System.out.println(contor);
    }
}
