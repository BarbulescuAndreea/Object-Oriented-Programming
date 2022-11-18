package lab2;

public class complex {
    private int real;
    private int imaginary;

    //constructor cu 2 parametri
    public complex(int real, int imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    //constructor fara niciun parametru
    public complex(){
        this(0,0);
    }

    //copy constructor
    public complex(complex Complex){
        this.real = Complex.real;
        this.imaginary = Complex.imaginary;
    }

    //setter si getter pt real
    public void setReal(int real){
        this.real = real;
    }
    public int getReal(){
        return this.real;
    }

    //setter si getter pt imaginary
    public void setImaginary(int imaginary){
        this.imaginary = imaginary;
    }
    public int getImaginary(){
        return this.imaginary;
    }

    public void addWithComplex(complex obj){
        int realsum = this.real + obj.real; //this.real campul real al nr cu care apelez fct in main
        int imaginarysum = this.imaginary + obj.imaginary; //this.imaginary e numarul care apeleaza fct
    }

    public void showNumber(){
        System.out.println("Numarul complex rezultat este %.1f + %.1fi:" + real + imaginary);
    }

}

