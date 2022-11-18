import java.util.ArrayList;

public class main {
    public static void main(String[] args){
           Lindt l1 = new Lindt("cioco", "India", 1, 2, 3);
           Lindt l2 = new Lindt("cioco", "India", 7, 8, 10);

        CandyBag bomboane = new CandyBag();
        System.out.println(l1.equals(l2));
        System.out.println(bomboane.addBomboane());

        Baravelli bo1 = new Baravelli("Strawberry & Chocolate", "France", (float)8.2, (float)4.3);
        System.out.println(bo1.printBaravelliDim(bo1));
        //System.out.println(bo1.getVolume());

        Area a1 = new Area(bomboane, 10, "Dna Ghica");
        System.out.println(a1.getBirthdayCard(a1));
        a1.noInstanceOfFunction(a1);
    }
}
