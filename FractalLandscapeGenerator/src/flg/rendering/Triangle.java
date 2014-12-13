package flg.rendering;

import javax.vecmath.Point3d;


public class Triangle {

    private Point3d coordinate1;
    private Point3d coordinate2;
    private Point3d coordinate3;


    public Triangle(Point3d coordinate1, Point3d coordinate2, Point3d coordinate3) {
        super();
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.coordinate3 = coordinate3;
    }


    public Point3d getCoordinate1() {
        return coordinate1;
    }


    public void setCoordinate1(Point3d coordinate1) {
        this.coordinate1 = coordinate1;
    }


    public Point3d getCoordinate2() {
        return coordinate2;
    }


    public void setCoordinate2(Point3d coordinate2) {
        this.coordinate2 = coordinate2;
    }


    public Point3d getCoordinate3() {
        return coordinate3;
    }


    public void setCoordinate3(Point3d coordinate3) {
        this.coordinate3 = coordinate3;
    }

}
