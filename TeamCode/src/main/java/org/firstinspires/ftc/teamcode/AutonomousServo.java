package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutonomousServo extends LinearOpMode {

    Servo jimmy;
    double pos;

    public void runOpMode() {

        jimmy = hardwareMap.get(Servo.class, "servo");

        jimmy.setPosition(0);
        pos = 0;

        waitForStart();

        while (opModeIsActive()) {

            for (double pos=0; pos<=1; pos=pos+0.05) {
                jimmy.setPosition(pos);
                sleep(50);

            }
            for (double pos=1; pos>=0; pos=pos-0.05) {
                jimmy.setPosition(pos);
                sleep(50);
            }



        }

    }

}