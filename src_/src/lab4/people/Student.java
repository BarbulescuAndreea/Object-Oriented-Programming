package lab4.people;

import lab4.database.Database;

import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private Map<String, Integer> subjects;

    public Student(String firstName, String lastName, Map<String, Integer> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.subjects = subjects;
    }

    // TODO: copy constructor
    public Student(Student otherStudent){
        this.firstName = new String(otherStudent.firstName);
        this.lastName = new String(otherStudent.lastName);
        this.subjects = new HashMap(otherStudent.subjects);
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

    public Map<String, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<String, Integer> subjects) {
        this.subjects = subjects;
    }

    public double averageGrade() {
        // TODO
        double averageGrade = 0;
        double noOfMaterii = 0;
        double grade = 0;
        for(Map.Entry <String, Integer> entry : getSubjects().entrySet()){
            grade += entry.getValue();
        }
        noOfMaterii = getSubjects().entrySet().size();
        averageGrade = grade/noOfMaterii;
        return averageGrade;
    }


    public List<Teacher> getAllTeachers() {
        // TODO
        List<Teacher> deepcopy = new ArrayList<>();
        for (var teacher : Database.getDatabase().findAllTeachers()) {
            deepcopy.add(new Teacher(teacher));
        }
        return Collections.unmodifiableList(deepcopy);
    }

    public int getGradeForSubject(String subject) { //numele materiei
        // TODO
        int notamaterie = 0;
        for(Map.Entry <String, Integer> entry : getSubjects().entrySet()){
            if(entry.getKey().equals(subject)){
                notamaterie = entry.getValue();
            }
        }
        return notamaterie;
    }

    @Override
    public String toString() {
        return "Student: " + firstName + " " + lastName + "\n"
                + "Subjects: " + subjects + "\n";
    }


    public List<Teacher> getTeachersBySubject(String subject) {
        // TODO
        List<Teacher> deep_copy = new ArrayList<>();
        for (var teacher : Database.getDatabase().findTeachersBySubject(subject)) {
            deep_copy.add(new Teacher(teacher));
        }
        return Collections.unmodifiableList(deep_copy);
    }

    public List<Student> getAllStudents() {
        // TODO
        List<Student> deep_copy = new ArrayList<>();
        for (var student : Database.getDatabase().findAllStudents()) {
            deep_copy.add(new Student(student));
        }
        return Collections.unmodifiableList(deep_copy);
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO
        List<Student> deep_copy = new ArrayList<>();
        for (var student : Database.getDatabase().getStudentsBySubject(subject)) {
            deep_copy.add(new Student(student));
        }
        return Collections.unmodifiableList(deep_copy);
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO
        List<Student> deep_copy = new ArrayList<>();
        for (var student : Database.getDatabase().getStudentsByAverageGrade()) {
            deep_copy.add(new Student(student));
        }
        return Collections.unmodifiableList(deep_copy);
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO
        List<Student> deep_copy = new ArrayList<>();
        for (var student : Database.getDatabase().getStudentsByGradeForSubject(subject)) {
            deep_copy.add(new Student(student));
        }
        return Collections.unmodifiableList(deep_copy);
    }
}
