
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
    game plan!
    Need encoders for linear slide + code for them
    Need to test all systems once robot is (theoretically) functioning
*/

package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.drive.opmode.subsystems.slideOpmode;

@TeleOp(name="Game Opmode", group="Linear Opmode")
//@Disabled
public class GameTeleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //drive motors
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

                // Initialize the hardware variables. Note that the strings used here as parameters
                // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).
            rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
            leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
            leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_drive");
            rightRearDrive = hardwareMap.get(DcMotor.class, "right_rear_drive");// To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.

            // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
            leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
            leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
            rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {

                double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed //power level for telemetry
                double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing //
                double rx = gamepad1.right_stick_x;


                // Denominator is the largest motor power (absolute value) or 1 // Mode
                // This ensures all the powers maintain the same ratio, // default below is POV.
                // but only if at least one is out of the range [-1, 1] //
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); //ght stick to turn.
                double frontLeftPower = (y + x + rx) / denominator; //is easier to drive straight.
                double backLeftPower = (y - x + rx) / denominator; //
                double frontRightPower = (y - x - rx) / denominator; //
                double backRightPower = (y + x - rx) / denominator; //) ;


                leftFrontDrive.setPower(frontLeftPower); //
                leftRearDrive.setPower(backLeftPower); //.
                rightFrontDrive.setPower(frontRightPower); //drive forward slowly and keep straight.
                rightRearDrive.setPower(backRightPower); //


                //e.toString());
                //%.2f)", leftPower, rightPower);
            }
            //
        }
    }

