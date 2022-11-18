package lab5.task1;
import java.util.*;

public class RandomOutTask implements Task {
    @Override
    public String toString() {
        return "random= " + Math.random();
    }
    public RandomOutTask(){
        Math.random();
    }
    public void print(){
        System.out.println(Math.random());
    }
    public void execute(){
        print();
    }
}
