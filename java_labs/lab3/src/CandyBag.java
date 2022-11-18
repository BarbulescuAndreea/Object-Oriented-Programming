import java.util.ArrayList;

class CandyBag{
    ArrayList<CandyBox> bomboane = new ArrayList<>();

    public ArrayList addBomboane(){
        CandyBox l1 = new Lindt("Chocolate", "Italian", 1, 2, 3);
        CandyBox l2 = new Lindt("Pandishpan", "British", 5, 6, 7);
        CandyBox b1 = new Baravelli("Strawberry & Chocolate", "France", 8, 4);
        CandyBox b2 = new Baravelli("Bubble Vanilla", "Irish", 10, 2);
        CandyBox b3 = new Baravelli("BrownBerry", "Bulgarian", 6, 3);
        CandyBox c1 = new ChocAmor("Vanills", "Greek" ,10);
        CandyBox c2 = new ChocAmor("Strawberry", "Greek" ,7);
        CandyBox c3 = new ChocAmor("Marmelade", "Spanish" ,8);
        CandyBox c4 = new ChocAmor("BlueBerry", "Romanian" ,12);

        bomboane.add(l1);
        bomboane.add(l2);
        bomboane.add(b1);
        bomboane.add(b2);
        bomboane.add(b3);
        bomboane.add(c1);
        bomboane.add(c2);
        bomboane.add(c3);
        bomboane.add(c4);

        return bomboane;
    }

    @Override
    public String toString() {
        return "CandyBag{" +
                "bomboane=" + bomboane +
                '}';
    }
}