package lab4.database;

import lab4.people.Student;
import lab4.people.Teacher;

import javax.naming.event.ObjectChangeListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.*;

public class Database {
    // TODO: make it Singleton
    private static final List<Student>  students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static int instanceCount = 0;

    private Database(){
    }

    public List<Student> getStudents(){

        return students;
    }

    public List<Teacher> getTeachers(){
        return teachers;
    }

    public static int getNumberOfInstances() {
        return instanceCount;
    }

    public void addTeachers(List<Teacher> teachers) {
        this.teachers.addAll(teachers);
    }

    public void addStudents(List<Student> students) {
        this.students.addAll(students);
    }

    private static Database instance = null;

    public static Database getDatabase() {
        if (instance==null) {
            instance = new Database();
            instanceCount++;
        }
        return instance;
    }

    public List<Teacher> findTeachersBySubject(String subject) {
        // TODO
        //numele unei materii si vreau lista de profesori care predau materia respectiva
        List <Teacher> profesoriiPredauX = new ArrayList<>();
        for(int i = 0; i < teachers.size(); i++){
            for(int k = 0; k < teachers.get(i).getSubjects().size(); k++){
                if(teachers.get(i).getSubjects().get(k) == subject) {
                    profesoriiPredauX.add(teachers.get(i));
                }
            }
        }
        return profesoriiPredauX;
    }

    public List<Student> findAllStudents() {
        // TODO

        return students;
    }

    public List<Teacher> findAllTeachers() {
        // TODO

        return teachers;
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO

        List<Student> studentiCuMateriaX = new ArrayList<>();
        for(Student s : students){
            for(Map.Entry<String, Integer> entry : s.getSubjects().entrySet()){
                if(entry.getKey().equals(subject)){
                    studentiCuMateriaX.add(s);
                }
            }
        }
        return studentiCuMateriaX;
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO
        List<Student> listaordonata = new ArrayList<>(students);
        listaordonata.sort(Comparator.comparing(Student::averageGrade));
        return listaordonata;
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO

        List<Student> studentiCuMateriaX = new ArrayList<>();
        studentiCuMateriaX = getDatabase().getStudentsBySubject(subject);
        studentiCuMateriaX.sort(Comparator.comparing(o->o.getGradeForSubject(subject)));
        //pt fiecare ob o pe care vrea sa il compare ii indica dupa ce sa compare
        return studentiCuMateriaX;
    }
}
