import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Planet {
    double gravityEarth;
    int ex = -2;
    int ey = 2;
    double gravitySaturn;
    int sx = 2;
    int sy = 2;
    double gravityMoon;
    int mx = 2;
    int my = 2;
    double y = 600;
    double x = 285;
    double earthX = 100;
    double earthY = 140;
    double moonX = 300;
    double moonY = 400;
    double saturnX = 350;
    double saturnY = 150;
    double angleEarth;
    double angleMoon;
    double angleSaturn;
    double speedY = 0;
    double speedX = -4;
    double ballRadius =25;
    double earthRadius=40;
    double moonRadius=60;
    double saturnRadius=40;
    BufferedImage earth = ImageIO.read(new File("C:\\Users\\user\\Downloads\\earth.png"));
    BufferedImage moon = ImageIO.read(new File("C:\\Users\\user\\Downloads\\mooon.png"));
    BufferedImage saturn = ImageIO.read(new File("C:\\Users\\user\\Downloads\\saturn.png"));
    BufferedImage space = ImageIO.read(new File("C:\\Users\\user\\Downloads\\space.jpg"));
    BufferedImage ball = ImageIO.read(new File("C:\\Users\\user\\Downloads\\ball.png"));
    public Planet() throws IOException {
    }
    public void drawBackground(Graphics g) {
        g.drawImage(space, 10, 50, 600, 600, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
    public void drawBallMoon(Graphics g) {
        g.drawImage(ball, (int) x - 2, (int) y - 2, 54, 54, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        g.drawImage(moon, (int) moonX - 45, (int) moonY, 210, 122, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        g.drawOval((int) x, (int) y, 50, 50);
        g.drawOval((int) moonX, (int) moonY, 120, 120);
    }
    public void drawSaturn(Graphics g) {
        g.drawImage(saturn, (int) saturnX - 30, (int) saturnY - 12, 143, 108, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        g.drawOval((int) saturnX, (int) saturnY, 80, 80);
    }

    public void drawEarth(Graphics g) {
        g.drawImage(earth, (int) earthX - 13, (int) earthY - 13, 103, 104, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        g.drawOval((int) earthX, (int) earthY, 80, 80);
    }
    void updatePlanets(int record, double dt, boolean collision) {
        if (y <= 10 + 40 & record>=3) {
            ex += Math.signum(ex) * dt;                        //увеличиваем модули скоростей
            ey += Math.signum(ey) * dt;
            mx += Math.signum(mx) * dt;
            my += Math.signum(my) * dt;
            sx += Math.signum(sx) * dt;
            sy += Math.signum(sy) * dt;
        }
        if (earthX <= 10 || earthX >= 150) {
            ex *= -1;
        }
        if (moonX <= 10 || moonX >= 450) {
            mx *= -1;
        }
        if (saturnX <= 230 || saturnX >= 530) {
            sx *= -1;
        }
        if (earthY<= 50 || earthY >= 150) {
            ey *= -1;
        }
        if (moonY <= 300 || moonY >= 400) {
            my *= -1;
        }
        if (saturnY <= 50 || saturnY >= 200) {
            sy *= -1;
        }
        if (record >= 2) {
            earthX += ex;
            earthY += ey;
        }
        if (record >= 1) {
            saturnX += sx;
            saturnY += sy;
        }
        moonX += mx;
        moonY += my;
    }
    void updateBall(int record) {
        angleMoon = Math.atan2(0.0 + (moonY - y), (0.0 + moonX - x));
        angleSaturn = Math.atan2(0.0 + (saturnY - y), (0.0 + saturnX - x));
        angleEarth = Math.atan2(0.0 + (earthY - y), (0.0 + earthX - x));
        gravityMoon = 7000 / ((moonX - x) * (moonX - x) + (moonY - y) * (moonY - y));
        if (record >= 1) {
            gravitySaturn = 3000 / ((saturnX - x) * (saturnX - x) + (saturnY - y) * (saturnY - y));
        } else {
            gravitySaturn = 0;
        }
        if (record >= 2) {
            gravityEarth = 3000 / ((earthX - x) * (earthX - x) + (earthY - y) * (earthY - y));
        } else {
            gravityEarth = 0;
        }
        speedY += (gravityEarth * Math.sin(angleEarth) + gravityMoon * Math.sin(angleMoon) + gravitySaturn * Math.sin(angleSaturn));
        speedX += (gravityEarth * Math.cos(angleEarth) + gravityMoon * Math.cos(angleMoon) + gravitySaturn * Math.cos(angleSaturn));
        x += speedX;
        y += speedY;
    }

}