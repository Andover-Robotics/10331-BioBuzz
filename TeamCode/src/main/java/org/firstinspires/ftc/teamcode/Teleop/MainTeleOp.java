package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    Bot bot;

    GamepadEx gp1, gp2;
    private Follower follower;


    private FtcDashboard ftcDash = FtcDashboard.getInstance();


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        follower = Constants.create(hardwareMap);

        while (opModeInInit() && !isStarted() && !isStopRequested()){
            TelemetryPacket packet = new TelemetryPacket();
            follower = Constants.create(hardwareMap);

            //put telemetry that needs to be displayed after initialization
            //also anything bot needs to prepare before teleop starts
            telemetry.update();
        }

        waitForStart();
        while (opModeIsActive() && !isStopRequested()){
            //driving controls
            double forward =-gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            follower.manual(forward, lateral, turn);
            Pose robotPose = follower.pose();

            //bot.intake.closeGate().schedule();

            bot.shooter.periodic();
            TelemetryPacket packet = new TelemetryPacket();
            gp1.readButtons();
            gp2.readButtons();

            //put all controller commands here
            //add any telemetry
            telemetry.addData("Robot X", robotPose.x());
            telemetry.addData("Robot Y", robotPose.y());
            telemetry.addData("Robot heading", Math.toDegrees(robotPose.heading()));



            follower.update();
            telemetry.update();
        }

    }
}
