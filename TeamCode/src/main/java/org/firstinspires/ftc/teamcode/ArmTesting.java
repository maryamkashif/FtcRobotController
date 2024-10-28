package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ArmTesting extends LinearOpMode {

    Arm arm;

    @Override
    public void runOpMode() {

        arm = new Arm();
        arm.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            /*if (gamepad1.a) {
                arm.goTo(400);
            }
            if (gamepad1.b) {
                arm.goTo(0);
            }*/

            arm.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            arm.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            if (gamepad1.b) {
                arm.arm.setPower(1);
            } else if (gamepad1.x) {
                arm.arm.setPower(-1);
            } else {
                arm.arm.setPower(0);
            }
        }

    }


}