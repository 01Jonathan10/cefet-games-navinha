package br.cefetmg.games.weapons;

import br.cefetmg.games.Asteroid;
import br.cefetmg.games.Config;
import br.cefetmg.games.collision.Collidable;
import br.cefetmg.games.collision.Collision;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Tiro laser.
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class LaserShot implements Shot {

    private static final float WIDTH = 4;
    private static final float HEIGHT = 14;
    private final Vector2 position;
    private final float speed;
    private final Rectangle bounds;
    private final Polygon polygon;

    LaserShot(Vector2 position) {
        this.position = new Vector2(position);
        bounds = new Rectangle(
                position.x - WIDTH / 2f, position.y - HEIGHT / 2f,
                WIDTH, HEIGHT);
        speed = 50;
        float[] vertices = {0, 0, 
                            WIDTH, 0, 
                            WIDTH, HEIGHT, 
                            HEIGHT, 0};
        polygon = new Polygon(vertices);
    }

    @Override
    public void update(float dt) {
        position.y += speed * dt;
        bounds.y = position.y - HEIGHT / 2F;
        polygon.setPosition(position.x, position.y);
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.PINK);
        renderer.identity();
        renderer.translate(position.x, position.y, 0);
        renderer.rect(-WIDTH / 2F, -HEIGHT / 2F, WIDTH, HEIGHT);
        if (Config.debug) {
            renderer.setColor(Color.YELLOW);
            renderer.identity();
            renderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public boolean isOutOfBounds(Rectangle area) {
        return !area.overlaps(bounds);
    }

    @Override
    public boolean collidesWith(Collidable other) {
        // Laser vs Asteroid: rect vs rect
        // Laser vs Ship: nada
        if (other instanceof Asteroid) {
//            return Collision.rectCircleOverlap(other.getMinimumEnclosingBall(), bounds);
            return Collision.polygonsOverlap(other.getPolygon(), polygon);
        } else {
            return false;
        }
    }
    
    @Override
    public Rectangle getMinimumBoundingRectangle() {
        return bounds;
    }

    @Override
    public Circle getMinimumEnclosingBall() {
        return null;
    }
    
    @Override
    public Polygon getPolygon(){
        return polygon;
    }
}
