import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {

    @Test
    public void testPoint(){

        double point_A_x = 5.0;
        double point_A_y = 5.0;
        double point_B_x = -4.0;
        double point_B_y = -4.0;


        Point point_A = new Point(point_A_x, point_A_y);
        Point point_B = new Point(point_B_x, point_B_y);

        double distance = Point.distance(point_A, point_B);

        assert String.format("%.2f", distance).equals("12,73");

        Assert.assertEquals(
                String.format("%.2f", distance),
                "12,73",
                "Дистанция не верна фактическому результату"
        );
    }
}
