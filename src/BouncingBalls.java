
import java.applet.Applet;
import java.awt.*;
import java.util.logging.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Albert
 */
public class BouncingBalls extends Applet implements Runnable {

    private Button btnSuspend, btnResume, btnPlusOne, btnMinusOne;
    private Thread animator;
    private boolean suspended = false;
    private Image offScrImage;
    private Graphics offScrGC;
    private Ball balls[];

    int w, h;
    int n = 5;
    int maxSpeed = 10;

    @Override
    public void init() {

        balls = new Ball[ n ];
        setSize(400, 400);
        this.setVisible(true);
        w = getWidth();
        h = getHeight() - 50;
        setLayout(new BorderLayout());
        
        balls[0] = new Ball(15, w, h);
        balls[1] = new Ball(15, w, h);
        balls[2] = new Ball(15, w, h);
        balls[3] = new Ball(15, w, h);
        balls[4] = new Ball(15, w, h);
        
        offScrImage = createImage(w, h);
        offScrGC = offScrImage.getGraphics();
        Panel pBtns = new Panel();
        btnSuspend = new Button("Suspend");
        pBtns.add(btnSuspend);
        btnResume = new Button("Resume");
        pBtns.add(btnResume);
        btnPlusOne = new Button("+1");
        pBtns.add(btnPlusOne);
        btnMinusOne = new Button("-1");
        pBtns.add(btnMinusOne);
        add(pBtns, BorderLayout.SOUTH);
    }

    @Override
    public boolean mouseUp( Event e, int x, int y ) {
        if( suspended ) {
            animator.resume();
            suspended = false;
        } else {
            animator.suspend();
            suspended = true;
        }
        return true;
    }


    @Override
    public void paint( Graphics g ) {
        g.drawImage( offScrImage, 0, 0, this );
    }

    @Override
    public void start() {
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void stop() {
        animator.stop();
        animator = null;
    }

    @Override
    public void run() {

        while (animator != null) {

            offScrGC.setColor(Color.white);
            offScrGC.fillRect(0, 0, w, h);

            for( int i = 0; i < n; i++ )
                balls[i].hitBy = null;

            for( int i = 0; i < n; i++ ) {
                balls[i].move();
                balls[i].bounceOffWalls(w, h);
                for( int j = 0; j < n; j++ ) {
                    if( i != j && balls[i].touches(balls[j]))
                        balls[i].hits( balls[j] );
                }
                balls[i].paint( offScrGC );
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(BouncingBalls.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint ();
        }
    }
}