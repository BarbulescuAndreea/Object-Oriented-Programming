import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentTest {
    private Student student;
    private Student s2 = new Student("Stefan", "Ion", 7588, 9);

    @BeforeEach
    public void setUp() {
        this.student = new Student("Stefan", "Andrei", 8555, 9);
    }

    @AfterEach
    public void clean() {
        this.student = null;
    }

    @Test
    @DisplayName("CompareTo test")
    public void testCompare() {
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(s2);
        Collections.sort(list);
        Student st1 = list.get(0);
        Student st2 = list.get(1);
        Assertions.assertEquals(st1, student);
        Assertions.assertEquals(st2, s2);
    }
}
