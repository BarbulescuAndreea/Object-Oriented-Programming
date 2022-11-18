import java.util.*;

public class Main {
    public static void main(String[] args){
        //ex 2
        Student s1 = new Student("Popescu", "Andrei", 5728, 9);
        Student s2 = new Student("Marinescu", "Ana", 4588, 10);
        Student s3 = new Student("Popescu", "Daniel", 2576, 9);
        Student s4 = new Student("Renat", "Mihnea", 4790, 7);
        Student s5 = new Student("Stefan", "Ioana", 3465, 7);

        ArrayList<Student> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        arr.add(s5);
        Collections.sort(arr);
        System.out.println(arr);

        //ex 3
        arr.sort(
                Comparator.comparingDouble((Student h) -> h.averageGrade));
        Collections.reverse(arr);
        System.out.println(arr);

        //ex 4
        PriorityQueue<Student> pq=
                new PriorityQueue<Student>(Comparator.comparingLong((Student h) -> h.id));
        pq.addAll(arr);
        while (pq.size() != 0)
        {
            System.out.println(pq.remove()); //acum sunt in ordinea id-ului
        }

        //ex 6
        HashMap<Student, LinkedList<String>> map = new HashMap<>();

        LinkedList<String> list = new LinkedList<>();
        list.add("Mate");
        list.add("Franceza");
        list.add("Istorie");
        list.add("Geografie");
        list.add("Fizica");
        list.add("Romana");
        list.add("Tehnologie");
        list.add("Chimie");

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("Sport");
        list2.add("Religie");
        list2.add("Ecotehnologii");
        list2.add("Psihologie");
        list2.add("Engleza");
        list2.add("Informatica");
        list2.add("TIC");

        LinkedList<String> newList = new LinkedList<>();
        newList.addAll(list);
        newList.addAll(list2);

        map.put(s1, list);
        map.put(s2, list2);
        map.put(s3, newList);
        for(Map.Entry<Student, LinkedList<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //ex 7
        NrPare<Integer> listt = new NrPare<>();
        listt.add(2);
        listt.add(3);
        listt.add(4);
        listt.add(0);
        listt.add(32);
        listt.add(77);
        listt.add(39);
        listt.add(1000);
        listt.add(755);
        for(Integer i : listt){
            System.out.println(i); //se observa ca vor fi adaugate doar nr pare
        }
        //daca este LinkedHashSet elementele sunt afisate in ordinea in care sunt inserate
        //daca inlocuiesc cu HashSet se schimba ordinea insa nu intr-un fel anume
        //daca inlocuiesc cu TreeSet se afiseaza in ordine crescatoare elementele pare

    }
}
