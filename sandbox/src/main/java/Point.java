public class Point {

    double point_x;
    double point_y;

    public Point(Double point_x, Double point_y){
        this.point_x = point_x;
        this.point_y = point_y;
    }

    public static double distance(Point a, Point b){
        double range = Math.sqrt(((a.point_x - b.point_x)*(a.point_x - b.point_x)) +
                ((a.point_y - b.point_y)*(a.point_y - b.point_y)));
        return range;
    }
}
