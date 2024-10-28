package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class IGTeleop extends LinearOpMode {

    public RobotDrive bot;
    public Elevator elevator;
    public Intake intake;
    public Arm arm;

    // Position of the arm when it's lifted
    public int top = 2380;
    public int middle = 1745;
    // Position of the arm when it's down
    public int downpos = 0;

    int slidesFloorIntaking = 0;
    int slidesSubIntaking = 590;


    @Override
    public void runOpMode() {

        bot = new RobotDrive();
        elevator = new Elevator();
        intake = new Intake();
        arm = new Arm();

        bot.init(hardwareMap);
        elevator.init(hardwareMap);
        intake.init(hardwareMap);
        arm.init(hardwareMap);

        boolean inSub = false;


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double jx = -gamepad1.left_stick_y;
            double jy = -gamepad1.left_stick_x;
            double jw = -gamepad1.right_stick_x;
            double m = 1;

            // elevator + arm intaking/outtaking positions
            if (gamepad1.right_bumper) {
                // pre intake pos
                elevator.elevate(slidesFloorIntaking, 0.5);
                arm.preIntake();

            }

            if (gamepad1.dpad_left) {
                // submersible
                if (inSub) {
                    elevator.elevate(slidesFloorIntaking, 0.5);
                    arm.preIntake();
                    inSub = false;
                } else {
                    elevator.elevate(slidesSubIntaking, 0.5);
                    arm.subintaking();
                    inSub = true;
                }
            }

            else if (gamepad1.dpad_up) {
                // high bucket
                elevator.elevate(top, 0.8);
                arm.outtaking();
            }

            else if (gamepad1.dpad_right) {
                // low bucket
                elevator.elevate(middle, 0.5);
            }

            if (gamepad1.a) {
                // home pos
                arm.home();
                elevator.elevate(downpos, 0.5);
            }

            //intake wheels

            // if the arm is at neutral already go straight to floor pos and intake
            if ((gamepad1.right_trigger > 0.05) && (arm.arm.getTargetPosition() == 750)) {
                elevator.elevate(slidesFloorIntaking, 0.5);
                arm.floorintaking();
                intake.intake();
            } else if (gamepad1.right_trigger > 0.05) {
                intake.intake();
            } else if (gamepad1.left_trigger > 0.05) {
                intake.outtake();
            } else {
                intake.stop();
            }

            // drivetrain slower
            if (elevator.elevatorLeft.getTargetPosition() > 2000) {
                m = 0.5;
            }

            // drivetrain
            bot.driveXYW(jx, jy, jw, m);

            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }

}
