package lab1.task2;

public class task3 {

    public static void main(String[] args){
        Student s1 = new Student();
        Student s2 = new Student();
        s1.setName("Andrei");
        s2.setName("Andrei");
        s1.setYear(2014);
        s2.setYear(2014);
        System.out.println(s1.equals(s2));
    }
}
