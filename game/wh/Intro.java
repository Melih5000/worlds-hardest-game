package game.wh;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


interface FadeListener {
    void fadeStarted(Intro i);
    void fadeCompleted(Intro i);
}

public class Intro extends JPanel {

    private final BufferedImage source;
    private final Timer timer;

    private float alpha = 1.0f;

    private final int duration = 4000; // 4 seconds
    private Long startTime;

    private boolean fadeOut = false;

    private FadeListener fadeListener;

    public Intro(BufferedImage source) {
        this.source = source;

        //Screen Size
        setPreferredSize(new Dimension(960,640));

        //Background Color
        setBackground(Color.BLACK);

        //Initialized timer with 5 ms
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime == null) {
                    startTime = System.currentTimeMillis();
                    fadeStarted();
                }

                long diff = System.currentTimeMillis() - startTime;

                alpha = (float)diff / (float)duration;

                if (alpha > 1.0) {
                    timer.stop();
                    alpha = 1.0f;
                    fadeCompleted();
                }
                if (fadeOut) {
                    alpha = 1.0f - alpha;
                }
                repaint();
            }
        });
    }

    public void setFadeListener(FadeListener listener) {
        fadeListener = listener;
    }

    protected void fadeStarted() {
        if (fadeListener != null) {
            fadeListener.fadeStarted(this);
        }
    }

    protected void fadeCompleted() {
        if (fadeListener != null) {
            fadeListener.fadeCompleted(this);
        }
    }

    public void reset() {
        timer.stop();
        alpha = 0;
        startTime = null;
    }

    public void fadeIn() {
        reset();
        fadeOut = false;
        timer.start();
    }

    public void fadeOut() {
        reset();
        fadeOut = true;
        timer.start();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(source, 0, 0,960,640, this);
        g2d.dispose();
    }

}
