package lab1.task2;
import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    String title;
    String description;
    Student[] students = new Student[20];
    ArrayList listastudenti = new ArrayList();
    int i = 0;

    public ArrayList filterYear(int year){
        ArrayList listafinala = new ArrayList();
        for(int i = 0; i < students.length; i++){
            if(students[i].getYear() == year) {
                listafinala.add(students[i]);
            }
        }
        return listafinala;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", students=" + Arrays.toString(students) +
                ", listastudenti=" + listastudenti +
                ", i=" + i +
                '}';
    }
}
