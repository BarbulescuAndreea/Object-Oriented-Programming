package lab5.task2;

import lab5.task1.Task;

import java.util.ArrayList;
import java.util.List;

public class Queue implements Container{
    private final ArrayList<Task> arr;
    public Queue(){
        arr = new ArrayList<>();
    }

    public int size(){
        return arr.size();
    }

    public boolean isEmpty(){
        return arr.isEmpty();
    }

    public void push(Task task){
        arr.add(task);
    }

    public Task pop(){
        if(isEmpty()){
            return null;
        }
        Task varf;
        varf = arr.get(0);
        arr.remove(0);
        System.out.println("FIFO: " +  varf);
        return varf;
    }

    public void transferFrom(Container container){
        Task t;
        while (container.size() != 0) {
            t = container.pop();
            push(t);
        }
    }

    public ArrayList<Task> getTasks() {
        return arr;
    }
}
