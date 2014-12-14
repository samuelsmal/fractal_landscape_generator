package flg.rendering;

import javax.vecmath.Point3f;


public class Triangle {

    private Point3f coordinate1;
    private Point3f coordinate2;
    private Point3f coordinate3;


    public Triangle(Point3f coordinate1, Point3f coordinate2, Point3f coordinate3) {
        super();
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.coordinate3 = coordinate3;
    }


    public Point3f getCoordinate1() {
        return coordinate1;
    }


    public void setCoordinate1(Point3f coordinate1) {
        this.coordinate1 = coordinate1;
    }


    public Point3f getCoordinate2() {
        return coordinate2;
    }


    public void setCoordinate2(Point3f coordinate2) {
        this.coordinate2 = coordinate2;
    }


    public Point3f getCoordinate3() {
        return coordinate3;
    }


    public void setCoordinate3(Point3f coordinate3) {
        this.coordinate3 = coordinate3;
    }

}
