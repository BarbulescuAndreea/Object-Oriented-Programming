import java.util.Objects;

public class Student implements Comparable<Student>{
    String name;
    String surname;
    long id;
    double averageGrade;

    //ex 5
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(student.averageGrade, averageGrade) == 0
                && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, averageGrade);
    }

    public Student(String name, String surname, long id, double averageGrade) {
        this.name = name;
        this.surname = surname;
        this.averageGrade = averageGrade;
        this.id = id;
    }

    @Override
    public int compareTo(Student o) {
        if(averageGrade == o.averageGrade){
            if(name.equals(o.name)){
                return surname.compareTo(o.surname);
            }else{
                return name.compareTo(o.name);
            }
        }else{
            return Double.compare(averageGrade, o.averageGrade);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
