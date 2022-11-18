package lab5.task2;

import lab5.task1.Task;

import java.util.ArrayList;

public class Stack implements Container{
    private final ArrayList<Task> arr;

    public Stack()
    {
        arr = new ArrayList<>();
       // capacity = TASK_NO;
        System.out.println("Stack");
    }

    public int size(){
        return arr.size();
    }

    public boolean isEmpty(){
        return arr.isEmpty();
    }

    public void push(Task task){
        arr.add(task);
        //System.out.println(arr[size]);
    }

    public Task pop(){
        if(isEmpty()){
            return null;
        }
       // System.out.println("LIFO: " + arr[size]);
        return arr.remove(arr.size()-1);
    }

    public void transferFrom(Container container) {
        //transfer from s
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
