package lab2;

public class Polygon {
    private Point[] puncte;

    public Polygon(int nrcolt) {

        puncte = new Point[nrcolt];
    }

    public Polygon (float coordonate[]) {
        this(coordonate.length / 2);
        for (int i = 0; i < coordonate.length; i += 2) {
            puncte[i] = new Point(coordonate[i], coordonate[i + 1]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : puncte) {
            sb.append(point);
        }
        /**
         * (1, 2)
         * (3, 5)
         * (10, 4)
         */

        /**
         * - Point (x, y) : (1, 2)\n
         * - Point (x, y) : (3, 5)\n
         */
        return sb.toString();
    }
}
