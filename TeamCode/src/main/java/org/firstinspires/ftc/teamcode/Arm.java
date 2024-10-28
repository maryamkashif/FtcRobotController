package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;



public class Arm {

    DcMotorEx arm;
    Servo pivot;

    public void init(HardwareMap hardwareMap) {
        arm = initDcMotor(hardwareMap, "arm", DcMotor.Direction.FORWARD);
        pivot = hardwareMap.get(Servo.class, "wrist");
        pivot.setPosition(0);
        PIDFController armPIDF = new PIDFController(0.002, 0, 0, 0);
    }

    public DcMotorEx initDcMotor(HardwareMap hardwareMap,
                                 String name,
                                 DcMotor.Direction dir) {
        DcMotorEx m = hardwareMap.get(DcMotorEx.class, name);
        m.setDirection(dir);
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m.setTargetPosition(0);
        m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setPower(0.5);
        return m;
    }

    public boolean isFloor() {
        return arm.getTargetPosition() == 925;
    }

    public void floorintaking() {
        pivot.setPosition(0.3);
        arm.setTargetPosition(890);
        arm.setPower(0.4);
    }

    public void subintaking() {
        pivot.setPosition(0.3);
        arm.setTargetPosition(1170);
        arm.setPower(1);
    }

    public void outtaking() {
        pivot.setPosition(0.3);
        arm.setTargetPosition(180);
        arm.setPower(1);
    }

    public void home() {
        pivot.setPosition(0);
        arm.setTargetPosition(0);
        arm.setPower(0.2);
    }

    public void preIntake() {
        pivot.setPosition(0.4);
        arm.setTargetPosition(750);
        arm.setPower(0.2);
    }

    public void goTo(int pos) {
        arm.setTargetPosition(pos);
        arm.setPower(1);
    }



}
