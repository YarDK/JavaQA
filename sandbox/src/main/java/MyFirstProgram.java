

public class MyFirstProgram {

    public static void main(String[] args) {

        double point_A_x = 3.0;
        double point_A_y = 4.3;
        double point_B_x = -2.0;
        double point_B_y = -12.3;


        Point point_A = new Point(point_A_x, point_A_y);
        Point point_B = new Point(point_B_x, point_B_y);

        double distance = point_A.distance(point_B);

        System.out.println("Расстояние между точкой A с координатами ("+point_A_x+";"+point_A_y+") и " +
                "B с координатами ("+point_B_x+";"+point_B_y+") = " + String.format("%.2f", distance));

    }


}
