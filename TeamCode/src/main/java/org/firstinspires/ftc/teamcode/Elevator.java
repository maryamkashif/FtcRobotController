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
        m.setTargetPosition(m.getCurrentPosition());
        m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setPower(0.5);
        return m;
    }

    public void elevate(int pos, double power) {
        elevatorRight.setTargetPosition(pos);
        elevatorLeft.setTargetPosition(pos);
        elevatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRight.setPower(power);
        elevatorLeft.setPower(power);
    }
}
