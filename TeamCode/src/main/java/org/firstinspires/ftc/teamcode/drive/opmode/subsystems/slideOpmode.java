package org.firstinspires.ftc.teamcode.drive.opmode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.opmode.GameTeleop;

@TeleOp (name="slideOpmode", group="linearOpmode")
public class slideOpmode extends GameTeleop {
    @Override
    public void runOpMode() throws InterruptedException {
        // Position of the arm when it's lifted
        int slideUpPosition = 1000;

        // Position of the arm when it's down
        int slideDownPosition = 0;

        // Find a motor in the hardware map named "Arm Motor"
        DcMotor slideMotor = hardwareMap.dcMotor.get("linear_slide");

        // Reset the motor encoder so that it reads zero ticks
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Sets the starting position of the arm to the down position
        slideMotor.setTargetPosition(slideDownPosition);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {
            // If the A button is pressed, raise the arm
            if (gamepad1.right_bumper) {
                slideMotor.setTargetPosition(slideUpPosition);
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(0.5);
            }

            // If the B button is pressed, lower the arm
            if (gamepad1.left_bumper) {
                slideMotor.setTargetPosition(slideDownPosition);
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(0.5);
            }

            // Get the current position of the armMotor
            double position = slideMotor.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPosition = slideMotor.getTargetPosition();

            // Show the position of the armMotor on telemetry
            telemetry.addData("Encoder Position", position);

            // Show the target position of the armMotor on telemetry
            telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();
        }
    }
}