package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class IntakeTestingIG extends LinearOpMode {

    Intake intake;

    @Override
    public void runOpMode() {

        intake = new Intake();

        intake.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.right_trigger > 0.05) {
                intake.intake();
            } else if (gamepad1.left_trigger > 0.05) {
                intake.outtake();
            } else {
                intake.stop();
            }
        }

    }
}