package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;

@TeleOp (name = "Shooter Tester", group = "testing")
public class ShooterTester extends OpMode {
    private Shooter shooter;
    GamepadEx gp1;

    private static final double incr_decr_RPM = 10; //increment/decrement the RPM
    

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        this.shooter = new Shooter(this);
        gp1 = new GamepadEx(gamepad1);
        shooter.enable();

    }

    @Override
    public void loop() {
        gp1.readButtons();
        if (gp1.wasJustPressed(GamepadKeys.Button.A)){
            Shooter.targetRPM += incr_decr_RPM;
        }
        if (gp1.wasJustPressed(GamepadKeys.Button.B)){
            Shooter.targetRPM -= incr_decr_RPM;
        }

        shooter.periodic();

        telemetry.addData("TargetRPM: ", shooter.getTargetRPM());
        telemetry.addData("CurrentRPM: ", shooter.getRPM());
        telemetry.addData("At Speed: ", shooter.checkSpeed());
        telemetry.update();

    }

}
