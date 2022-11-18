package lab5.task3;

public class Operation implements Minus, Plus, Mult, Div{
    float number;
    public Operation(float number){
        this.number = number;
        //System.out.println(this.number);
    }
    public void Minus(float number){
        this.number = this.number - number;
        //System.out.println(this.number);
    }
    public void Plus(float number){
        this.number = this.number + number;
        //System.out.println(this.number);
    }
    public void Mult(float number){
        this.number = this.number * number;
        //System.out.println(this.number);
    }
    public void Div(float number){
        if(number == 0){
            return;
        }
        this.number = this.number/number;
        //System.out.println(this.number);
    }

    public float getNumber() {
        return this.number;
    }
}
