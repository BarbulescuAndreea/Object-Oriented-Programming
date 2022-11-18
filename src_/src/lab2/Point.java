package lab2;

public class Point {
    float c1;
    float c2;

    //constructor care sa primeasca cele doua numere reale
    public Point(float c1, float c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    //metoda changeCoords - schimba coordonatele punctului apelat
    public void changeCoords(float nr1, float nr2){
        this.c1 = nr1;
        this.c2 = nr2;
    }

    @Override
    public String toString() {
        return "Punctul (x, y) : " + "(" +c1  + "," + c2 + ")" + "\n";
    }
}
