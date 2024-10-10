package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Laterator {

    Servo leftLinkage;
    Servo rightLinkage;

    public void init(HardwareMap hardwareMap) {
        leftLinkage = hardwareMap.get(Servo.class, "leftLinkage");
        rightLinkage = hardwareMap.get(Servo.class, "rightLinkage");
        leftLinkage.setPosition(0);
        rightLinkage.setPosition(1);
    }

    public void retract() {
        leftLinkage.setPosition(0);
        rightLinkage.setPosition(1);
    }

    public void extend() {
        leftLinkage.setPosition(1);
        rightLinkage.setPosition(0);
    }

}
