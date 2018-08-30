package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Vector2;

public class Collision {

    public static final boolean circlesOverlap(Circle c1, Circle c2) {
        Vector2 distance = new Vector2(c1.x - c2.x, c1.y - c2.y);
        return distance.x*distance.x + distance.y*distance.y <= (c1.radius + c2.radius)*(c1.radius + c2.radius);
    }

    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {
        boolean x_overlap = r1.x < r2.x + r2.width && r2.x < r1.x + r1.width;
        boolean y_overlap = r1.y < r2.y + r2.height && r2.y < r1.y + r1.height;
        return x_overlap && y_overlap;
    }
    
    public static final boolean rectCircleOverlap(Circle c, Rectangle r) {
        Vector2 distance = new Vector2(c.x - (r.x + r.width/2), c.y - (r.y + r.height/2));
        distance = distance.clamp(r.width/2, r.height/2);
        Vector2 closest_point = new Vector2(r.x + r.width/2 + distance.x, r.y + r.height/2 + distance.y);
        distance = new Vector2(closest_point.x - c.x, closest_point.y - c.y);
        return distance.x*distance.x + distance.y*distance.y <= c.radius*c.radius;
    }
    
    public static final boolean polygonsOverlap(Polygon p1, Polygon p2) {
        boolean is_colliding = Intersector.overlapConvexPolygons(p1,p2);
        System.out.println(is_colliding);
        return is_colliding;
    }
}
