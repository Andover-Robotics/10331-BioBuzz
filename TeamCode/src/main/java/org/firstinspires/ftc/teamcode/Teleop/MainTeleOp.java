package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    Bot bot;

    GamepadEx gp1, gp2;

    private FtcDashboard ftcDash = FtcDashboard.getInstance();


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);

        while (opModeInInit() && !isStarted() && !isStopRequested()){
            TelemetryPacket packet = new TelemetryPacket();

            //put telemetry that needs to be displayed after initialization
            //also anything bot needs to prepare before teleop starts
            telemetry.update();
        }

        waitForStart();
        while (opModeIsActive() && !isStopRequested()){
            TelemetryPacket packet = new TelemetryPacket();
            gp1.readButtons();
            gp2.readButtons();
            drive();

            //put all controller commands here
            //add any telemetry
            telemetry.update();
        }

    }




    public void drive(){
        //figure out driving
    }
}
