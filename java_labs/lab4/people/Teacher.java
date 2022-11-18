package lab4.people;

import lab4.database.Database;

import java.util.List;
import java.util.ArrayList;

public class Teacher {
    private String firstName;
    private String lastName;
    private List<String> subjects;

    public Teacher(String firstName, String lastName, List<String> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    // TODO: copy constructor
    public Teacher(Teacher object){
        this.firstName = object.firstName;
        this.lastName = object.lastName;
        this.subjects = object.subjects;
    }

    /*public Teacher(Teacher teacher) {
        this.firstName = new String(teacher.firstName);
        this.lastName = new String(teacher.lastName);
        this.subjects = new List<String>(teacher.subjects);
    }*/

    @Override
    public String toString() {
        return "Teacher: " + firstName + " " + lastName + "\n"
                + "Subjects: " + subjects + "\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Teacher> getAllTeachers() {
        // TODO
        List<Teacher> listadeprofi = new ArrayList<>();
        listadeprofi = Database.getDatabase().findAllTeachers();
        return listadeprofi;
    }

    public List<Teacher> getTeachersBySubject(String subject) {
        // TODO
        /*List<Teacher> teacherBySubject = new ArrayList<>();
        teacherBySubject = Database.findTeachersBySubject(subject);
        return teacherBySubject;*/
        return Database.getDatabase().findTeachersBySubject(subject);
    }

    public List<Student> getAllStudents() {
        // TODO
        List<Student> listadestud = new ArrayList<>();
        listadestud = Database.getDatabase().findAllStudents();
        return listadestud;
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO
        List<Student> studentsBySubject = new ArrayList<>();
        studentsBySubject = Database.getDatabase().getStudentsBySubject(subject);
        return studentsBySubject;
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO
        List<Student> studentsBySubject = new ArrayList<>();
        return studentsBySubject = Database.getDatabase().getStudentsByAverageGrade();
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO
        List<Student> studentsByGradeForSubject = new ArrayList<>();
        return studentsByGradeForSubject = Database.getDatabase().getStudentsByGradeForSubject(subject);
    }
}
