

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Start program");

        Point point_a = new Point(3.0, 4.3);
        Point point_b = new Point(-2.0, 12.3);

        System.out.println(Point.distance(point_a, point_b));

    }


}
