import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlanetPanel extends JPanel {
    Planet planet = new Planet();
    boolean start = false;
    JLabel Label = new JLabel("F I N I S H");
    JLabel Life = new JLabel("8");
    JLabel Record = new JLabel("0");

    double dtDouble;
    long prevTime;
    long dt;
    boolean crash = false;
    int life = 8;
    int record;
    int rectX = 10;
    int rectY = 10;
    int rectWidth = 600;
    int rectHeight = 640;
    int mouseX;
    int mouseY;

    public PlanetPanel() throws IOException {
        this.prevTime = System.currentTimeMillis();
    }

    void checkBallOutOfScreenCollision() {
        if (planet.x + 50 > 600 + 10) {
            planet.speedX *= -1;
        }
        if (planet.x < 10) {
            planet.speedX *= -1;
        }
        if (planet.y + 50 > 640 + 10) {
            planet.speedY *= -1;
        }
        if (planet.y <= 10 + 40) {
            start = false;
            planet.x = 285;
            planet.y = 600;
            if (record >= 3) {
                dtDouble *= 1.05;
            }
            record += 1;
            Record.setText(String.valueOf(record));
        }
    }

    int q = 0;

    @Override
    protected void paintComponent(Graphics g) {
        planet.drawBackground(g);
        if (crash) {
            g.setColor(new Color(0xFA372E));
            g.fillRect(235, 300, 150, 30);
            g.setColor(new Color(0x190005));
            g.drawString("G A M E   O V E R", 260, 320);
            start = false;
        } else {
            g.drawRect(rectX, rectY, rectWidth, rectHeight);
            g.drawRect(rectX, rectY, rectWidth, 40);
            if (!(start)) {
                g.setColor(new Color(0xFA372E));
                g.drawLine(mouseX, mouseY, 310, 625);
                g.setColor(new Color(0x190005));
            }
            planet.drawBallMoon(g);
            if (record == 1) {
                planet.drawSaturn(g);
            }
            if (record >= 2) {
                planet.drawSaturn(g);
                planet.drawEarth(g);
            }
        }
        long curTime = System.currentTimeMillis();
        dt = curTime - this.prevTime;
        if (q < 1) {
            dtDouble = (double) dt;
        }
        q += 1;
        this.prevTime = curTime;
    }

    void gameOver() {
        if (life == 0) {
            crash = true;
            record = 0;
            Record.setText(String.valueOf(record));
            life = 8;
            Life.setText(String.valueOf(life));
            planet.ex = -2;
            planet.ey = 2;
            planet.sx = 2;
            planet.sy = 2;
            planet.mx = 2;
            planet.my = 2;
        }
    }
    boolean collisionWithEarthSatutnMoon(){
        if (((planet.earthX + planet.earthRadius - planet.x - planet.ballRadius) * (planet.earthX + planet.earthRadius - planet.x - planet.ballRadius) + (planet.earthY + planet.earthRadius - planet.y - planet.ballRadius) * (planet.earthY + planet.earthRadius - planet.y - planet.ballRadius) <= 65 * 65 ||
                (planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) * (planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) + (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) * (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) <= 85 * 85 ||
                (planet.saturnX + planet.saturnRadius - planet.x - planet.ballRadius) * (planet.saturnX + planet.saturnRadius - planet.x - planet.ballRadius) + (planet.saturnY + planet.saturnRadius - planet.y - planet.ballRadius) * (planet.saturnY + planet.saturnRadius - planet.y - planet.ballRadius) <= 65 * 65) & (record >= 2)) {
            return true;
        }
        return false;
    }
    boolean collisionWithMoonSaturn(){
        if(((planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) * (planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) + (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) * (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) <= 85 * 85 ||
                (planet.saturnX + planet.saturnRadius - planet.x - planet.ballRadius) * (planet.saturnX + planet.saturnRadius - planet.x - planet.ballRadius) + (planet.saturnY + planet.saturnRadius - planet.y - planet.ballRadius) * (planet.saturnY + planet.saturnRadius - planet.y - planet.ballRadius) <= 65 * 65) & record == 1){
            return true;
        }
        return false;
    }
    boolean collisionWithMoon(){
        if(((planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) * (planet.moonX + planet.moonRadius - planet.x - planet.ballRadius) + (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) * (planet.moonY + planet.moonRadius - planet.y - planet.ballRadius) <= 85 * 85) & (record == 0)){
            return true;
        }
        return false;
    }
    boolean checkPlanetCollision() {
        if (collisionWithEarthSatutnMoon() || collisionWithMoonSaturn() || collisionWithMoon() ) {
            start = false;
            planet.x = 285;
            planet.y = 600;
            life -= 1;
            Life.setText(String.valueOf(life));
            gameOver();
            return true;
        }

        return false;
    }
}

