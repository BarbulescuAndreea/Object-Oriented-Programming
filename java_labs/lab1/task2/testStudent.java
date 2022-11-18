package lab1.task2;
import java.util.ArrayList;

public class testStudent {

    public static void main(String[] args) {
        Course curs = new Course();
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        ArrayList listStudenti = new ArrayList();
        s1.setName("Marius");
        s2.setName("Andreea");
        s3.setName("Ioana");
        s1.setYear(2010);
        s2.setYear(2010);
        s3.setYear(2013);
        curs.students = new Student[3];
        curs.students[0] = s1;
        curs.students[1] = s2;
        curs.students[2] = s3;
        listStudenti = curs.filterYear(Integer.parseInt(args[0]));
        System.out.println(listStudenti);
    }
}
