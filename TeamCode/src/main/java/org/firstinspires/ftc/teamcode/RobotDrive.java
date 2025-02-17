package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.teamcode.RobotValues.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class RobotDrive {

    DcMotorEx lf;
    DcMotorEx rf;
    DcMotorEx lb;
    DcMotorEx rb;
    IMU imu;
    static double headingOffset = 0; // static remembers across opmode runs

    public void init(HardwareMap hardwareMap) {
        lf = initDcMotor(hardwareMap, "fl", LEFTDIR);
        rf = initDcMotor(hardwareMap, "fr", RIGHTDIR);
        lb = initDcMotor(hardwareMap, "bl", LEFTDIR);
        rb = initDcMotor(hardwareMap, "br", RIGHTDIR);
        initIMU(hardwareMap);
    }

    public void initIMU(HardwareMap hardwareMap) {
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters params = new IMU.Parameters(
                new RevHubOrientationOnRobot(LOGO_DIR, USB_DIR));
        imu.initialize(params);
    }

    public double getIMUHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public void setHeading(double h) {
        headingOffset = h - getIMUHeading();
    }

    public double getHeading() {
        return headingOffset + getIMUHeading();
    }

    public DcMotorEx initDcMotor(HardwareMap hardwareMap,
                                 String name,
                                 DcMotor.Direction dir) {
        DcMotorEx m = hardwareMap.get(DcMotorEx.class, name);
        m.setDirection(dir);
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return m;
    }

    public void driveXYW(double rx, double ry, double rw, double m) {
        double denom = Math.max(1,
                (Math.abs(rx)+Math.abs(ry)+Math.abs(rw)));

        double lfPower = (rx - ry - rw) / denom;
        double rfPower = (rx + ry + rw) / denom;
        double lbPower = (rx + ry - rw) / denom;
        double rbPower = (rx - ry + rw) / denom;

        lf.setPower(m*lfPower);
        rf.setPower(m*rfPower);
        lb.setPower(m*lbPower);
        rb.setPower(m*rbPower);
    }

    public void driveFieldXYW(double fx, double fy, double fw) {
        // rotate field orientation to robot orientation
        double theta = Math.toRadians(getHeading());
        double rx = fx * Math.cos(-theta) - fy * Math.sin(-theta);
        double ry = fx * Math.sin(-theta) + fy * Math.cos(-theta);

        driveXYW(rx, ry, fw, 1);
    }

}
