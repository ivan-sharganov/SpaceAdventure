import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame();
        JLabel countLife = new JLabel("Lifes:");
        JLabel countRecord = new JLabel("Record:");
        PlanetPanel panel = new PlanetPanel();

        panel.add(panel.Label);
        panel.add(panel.Life);
        panel.add(panel.Record);
        panel.add(countLife);
        panel.add(countRecord);

        panel.setLayout(null);

        panel.Label.setBounds(180, -472, 1000, 1000);
        panel.Label.setFont(new Font("Verdana", Font.PLAIN, 50));

        countRecord.setBounds(530, -472, 1000, 1000);
        countLife.setBounds(17, -472, 1000, 1000);
        panel.Life.setBounds(55, -472, 1000, 1000);
        panel.Record.setBounds(590, -472, 1000, 1000);

        frame.setSize(683, 736);
        frame.setVisible(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.crash){
                    panel.crash =false;
                    panel.start=false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (panel.start==false) {
                    panel.planet.speedX = -(318 - e.getX()) / 40;
                    panel.planet.speedY = -(655 - e.getY()) / 40;
                }
                panel.start = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!panel.start && e.getY() <= 650 && e.getX() >= 10 && e.getX() < 610 && e.getY() >= 40 + 10) {
                    panel.mouseX = e.getX();
                    panel.mouseY = e.getY();
                }

            }
        });

        while (true) {
            frame.repaint();
            panel.checkBallOutOfScreenCollision();
            panel.checkPlanetCollision();
            if (panel.start) {
                panel.planet.updateBall(panel.record);
                panel.planet.updatePlanets(panel.record,0.01*panel.dtDouble, panel.crash);

            }
            Thread.sleep(10);
        }
    }

}
