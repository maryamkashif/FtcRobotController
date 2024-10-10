package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotDrive {

    DcMotorEx lf;
    DcMotorEx rf;
    DcMotorEx lb;
    DcMotorEx rb;

    public void init(HardwareMap hardwareMap) {
        lf = initDcMotor(hardwareMap, "lf", DcMotor.Direction.REVERSE);
        rf = initDcMotor(hardwareMap, "rf", DcMotor.Direction.FORWARD);
        lb = initDcMotor(hardwareMap, "lb", DcMotor.Direction.REVERSE);
        rb = initDcMotor(hardwareMap, "rb", DcMotor.Direction.FORWARD);
    }
    public DcMotorEx initDcMotor(HardwareMap hardwareMap,
                                 String name,
                                 DcMotor.Direction dir) {
        DcMotorEx m = hardwareMap.get(DcMotorEx.class, name);
        m.setDirection(dir);
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return m;
    }

    public void driveXYW(double rx, double ry, double rw) {
        double denom = Math.max(1,
                (Math.abs(rx)+Math.abs(ry)+Math.abs(rw)));

        double lfPower = (rx - ry - rw) / denom;
        double rfPower = (rx + ry + rw) / denom;
        double lbPower = (rx + ry - rw) / denom;
        double rbPower = (rx - ry + rw) / denom;

        lf.setPower(lfPower);
        rf.setPower(rfPower);
        lb.setPower(lbPower);
        rb.setPower(rbPower);
    }

}
