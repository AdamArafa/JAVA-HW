
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Albert
 */
public class Ball {
    private int rx, ry;
    private int vx, vy;
    private int radius;
    private final int speed = 10;
    private Color c;
    public Ball hitBy;

    public Ball(int radius, int w, int h) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        int d = Math.ceil(Math.random()) == 0 ? -1 : 1;
        c = new Color(r, g, b);

        this.radius = radius;
        rx = (int) Math.ceil(Math.random() * w);
        ry = (int) Math.ceil(Math.random() * h);
        vx = (int) Math.ceil(speed * d);
        vy = (int) Math.ceil(speed * d);
        hitBy = null;
    }

    public void move() {
        rx += vx;
        ry += vy;
    }

    public void speed(int sx, int sy) {
        vx = sx;
        vy = sy;
    }

    public void paint(Graphics g) {
        int d = radius * 2;
        g.setColor(c);
        g.fillOval(rx - radius, ry - radius,  d, d);
    }

    public boolean touches(Ball other) {
        int dx = other.rx - rx;
        int dy = other.ry - ry;
        int rr = radius + other.radius;
        int d = dx * dx + dy * dy;
        rr *= rr;
        return d <= rr;
    }

    public void hits(Ball other) {
        int temp = vx;
        vx = other.vx;
        other.vx = temp;

        temp = vy;
        vy = other.vy;
        other.vy = temp;

        this.hitBy  = other;
        other.hitBy = this;
    }

    public void bounceOffWalls(int w, int h) {
        if(((rx-radius) <= 0 && vx < 0) || ((rx+radius) >= w && vx > 0))
            vx = -vx;
        if(((ry-radius) <= 0 && vy < 0) || ((ry+radius) >= h && vy > 0))
            vy = -vy;
    }
}
