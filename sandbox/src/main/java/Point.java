public class Point {

    double point_x;
    double point_y;

    public Point(Double point_x, Double point_y){
        this.point_x = point_x;
        this.point_y = point_y;
    }


    public double distance(Point b){
        return Math.sqrt(((this.point_x - b.point_x)*(this.point_x - b.point_x)) +
                ((this.point_y - b.point_y)*(this.point_y - b.point_y)));
    }
}
