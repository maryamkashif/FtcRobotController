package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class IGTeleop extends LinearOpMode {

    public RobotDrive bot;
    public Elevator elevator;
    public Intake intake;
    public Laterator laterator

    // Position of the arm when it's lifted
    public int pos1 = 1500;
    public int pos2 = 2300;
    public int pos3 = 3500;
    // Position of the arm when it's down
    public int downpos = 0;

    @Override
    public void runOpMode() {

        bot = new RobotDrive();
        elevator = new Elevator();
        intake = new Intake();
        laterator = new Laterator();

        bot.init(hardwareMap);
        elevator.init(hardwareMap);
        intake.init(hardwareMap);
        laterator.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double jx = -gamepad1.left_stick_y;
            double jy = -gamepad1.left_stick_x;
            double jw = -gamepad1.right_stick_x;

            // elevator
            if (gamepad1.dpad_down) {
                elevator.elevate(downpos, 0.5);
            }

            else if (gamepad1.dpad_left) {
                elevator.elevate(pos1, 0.5);
            }

            else if (gamepad1.dpad_up) {
                elevator.elevate(pos2, 0.5);
            }

            else if (gamepad1.dpad_right) {
                elevator.elevate(pos3, 0.5);
            }

            //linkage
            if (gamepad1.right_bumper) {
                laterator.extend();
            }
            if (gamepad1.left_bumper) {
                laterator.retract();
            }

            //intake
            /*intake.intake();
            intake.outtake();*/

            // drivetrain
            bot.driveXYW(jx, jy, jw);

            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }

}
