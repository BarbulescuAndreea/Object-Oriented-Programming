package lab11.task2;

public class MyMatrix implements Summable{
    private double[][] m;

    public MyMatrix(int rows, int columns)
    {
        m = new double[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                m[i][j] = 0;
            }
        }
    }

    @Override
    public void addValue(Summable value) {
    }
}
