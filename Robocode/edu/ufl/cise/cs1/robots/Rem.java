package edu.ufl.cise.cs1.robots;

import com.sun.deploy.security.SelectableSecurityManager;
import robocode.*;
import robocode.util.Utils;

import java.awt.*;

/** Some ideas were based on features from sample robots provided in the project.
 * Source Code: package sample
 * Authors: Mathew A. Nelson (original), Flemming N. Larsen (contributor)
 */

public class Rem extends TeamRobot {

    double gunTurn;
    byte moveDirection = 1;

    public void run() {


        setColors(Color.white, Color.orange, Color.white, Color.black, Color.white);//body,gun,radar


        while (true) {
            ahead(Math.random()*100);
            stop();
            ahead(Math.random()*100);
            setTurnRight(45);
            turnGunRight(360);

            }
        }



        public void onScannedRobot (ScannedRobotEvent e){


            double distance = e.getDistance(); //obtain the distance at which the scanned robot is at.
            if ((isTeammate(e.getName()))) {
                doNothing();
            }

            if (getEnergy() > 80 && distance > 1000) {//depending on the distance of the scanned robot, fire with different power.
                fire(3);
            } else if (getEnergy() <= 80 && distance > 400 && distance <= 1000) {
                fire(2.5);
            } else if (distance > 200 && distance <= 400) {
                fire(1.5);
            } else if (distance < 200) {
                fire(1);
            }
        }


        /**
         * Ram into a robot idea from:
         * RamFire : Sample Package
         * Mathew A. Nelson (original), Flemming N. Larsen (contributor)
         *
         */

        public void onHitRobot (HitRobotEvent e){
            if ((isTeammate(e.getName()))) {
                doNothing();
            }

            if (e.getBearing() > -100 && e.getBearing() < 100) {
                ahead(e.getBearing());
                gunTurn = Utils.normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
                turnGunRight(gunTurn);
                if (e.getEnergy() > 60) {
                    fire(3);
                    ahead(100);
                } else if (e.getEnergy() > 50) {
                    fire(2);
                    ahead(50);
                } else if (e.getEnergy() > 30) {
                    fire(1);
                    ahead(500); // Ram him again!
                }
            }

        }
    }













