
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    private ArrayList<Ball> balls;

    int w, h;
    int maxSpeed = 10;

    @Override
    public void init() {

        balls = new ArrayList<>();
        setSize(400, 400);
        this.setVisible(true);
        w = getWidth();
        h = getHeight() - 50;
        setLayout(new BorderLayout());
        
        balls.add(new Ball(15, w, h));
        
        offScrImage = createImage(w, h);
        offScrGC = offScrImage.getGraphics();
        Panel pBtns = new Panel();
        btnSuspend = new Button("Suspend");
        btnSuspend.addActionListener(new btnListener());
        pBtns.add(btnSuspend);
        btnResume = new Button("Resume");
        btnResume.addActionListener(new btnListener());
        pBtns.add(btnResume);
        btnPlusOne = new Button("+1");
        btnPlusOne.addActionListener(new btnListener());
        pBtns.add(btnPlusOne);
        btnMinusOne = new Button("-1");
        btnMinusOne.addActionListener(new btnListener());
        pBtns.add(btnMinusOne);
        add(pBtns, BorderLayout.SOUTH);
    }

    class btnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSuspend) {
                if (!suspended) {
                    stop();
                    suspended = true;
                }
            }
            else if (e.getSource() == btnResume) {
                if (suspended) {
                    start();
                    suspended = false;
                }
            }
            else if (e.getSource() == btnPlusOne) {
                balls.add(new Ball(15, w, h));
            }
            else if (e.getSource() == btnMinusOne) {
                if (balls.size() > 1)
                    balls.remove(0);
            }
        }
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

            for (Ball ball : balls) {
                ball.hitBy = null;
            }

            for (Ball ball : balls) {
                ball.move();
                ball.bounceOffWalls();
                for (int i = 0; i < balls.size(); ++i) {
                    if (!ball.equals(balls.get(i)) && ball.touches(balls.get(i)))
                        ball.hits(balls.get(i));
                }
                ball.paint(offScrGC);
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