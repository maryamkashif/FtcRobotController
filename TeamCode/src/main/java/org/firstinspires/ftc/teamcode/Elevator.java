package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    DcMotorEx elevatorLeft;
    DcMotorEx elevatorRight;

    public void init(HardwareMap hardwareMap) {
        elevatorLeft = initDcMotor(hardwareMap, "elevatorLeft", DcMotor.Direction.REVERSE);
        elevatorRight = initDcMotor(hardwareMap, "elevatorRight", DcMotor.Direction.FORWARD);
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

    public void elevate(int pos, double power) {
        elevatorRight.setTargetPosition(pos);
        elevatorLeft.setTargetPosition(pos);
        elevatorRight.setPower(power);
        elevatorLeft.setPower(power);
    }
}
