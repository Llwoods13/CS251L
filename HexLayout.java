import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HexLayout {
    public HexLayout(Orientation orientation, Point size, Point origin) {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }
    public final Orientation orientation;
    public final Point size;
    public final Point origin;
    public static Orientation FlatTop = new Orientation(Math.sqrt(3.0),
            Math.sqrt(3.0)/2.0,0.0,3.0/2.0,Math.sqrt(3.0)/3.0,
            -1.0/3.0,0.0,2.0/3.0,0.5);
    public static Orientation PointyTop = new Orientation(3.0/2.0,0.0,
            Math.sqrt(3.0)/2.0,Math.sqrt(3.0),2.0/3.0,0.0,-1.0/3.0,
            Math.sqrt(3.0)/3.0,0.0);

    public Point hexToPixel(Hex h){
        Orientation M = orientation;
        double x = (M.f0 * h.q + M.f1 * h.r) * size.x();
        double y = (M.f2 * h.q + M.f3 * h.r) * size.y();
        return new Point(x + origin.x(), y + origin.y());
    }

    public Hex pixelToHex(Point p){
        double x = (p.x() - origin.x() / size.x());
        double y = (p.y() - origin.y()) / size.y();

        Orientation M = orientation;
        double q = M.b0 * p.x() + M.b1 * p.y();
        double r = M.b2 * p.x() + M.b3 * p.y();
        double s = -q - r;
        return new Hex((int) Math.round(q), (int) Math.round(r), 
                (int) Math.round(s));
    }

    public Point hexCornerOffset(int corner){
        Orientation M = orientation;
        double angle = 2.0 * Math.PI *(M.startAngle - corner) / 6.0;
        return new Point(size.x() * Math.cos(angle),
                size.y() * Math.sin(angle) );
    }

    public ArrayList<Point> polygonCorners(Hex h){
        ArrayList<Point> corners = new ArrayList<Point>(){{}};
        Point center = hexToPixel(h);
        for (int i = 0; i < 6; i++){
            Point offset = hexCornerOffset(i);
            corners.add(new Point(center.x() + offset.x(),
                    center.y() + offset.y()));
        }
        return corners;
    }

}
