import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class NrPare<E> extends LinkedHashSet<Integer> {

    @Override public boolean add(Integer o){
        if(o % 2 != 0){
            return false;
        }
        return super.add(o);
    }
}
