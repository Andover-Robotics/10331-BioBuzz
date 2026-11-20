package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;
//test everything
@TeleOp (name = "Transfer Test", group = "testing")
public class TransferTester extends OpMode{
    public Intake intake;
    GamepadEx gp1, gp2;
    public double intake_power = 0.7;
    public Shooter shooter;
    private static final double incr_decr_RPM = 50;

    @Override
    public void init() {
        //intake stuff
        this.intake = new Intake(this);

        //shooter stuff
        this.shooter = new Shooter(this);
        shooter.enable();

        //misc
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        telemetry.addData("Status","Initialized. Press Play");
        telemetry.update();
    }

    @Override
    public void loop(){

        //use this to test intake
        gp1.readButtons();
        gp2.readButtons();

        if (gp1.wasJustPressed(GamepadKeys.Button.A)) {
            intake.setPower(intake_power);
        }
        if (gp1.wasJustPressed(GamepadKeys.Button.B)){
            intake.reverseIntake(intake_power);
        }
        if (gp1.wasJustPressed((GamepadKeys.Button.X))){
            intake.stopIntake();
        }
        //testing gate
        if (gp1.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            intake.closeGate();
        }
        if (gp1.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            intake.openGate();
        }

        //testing tranfer
        if(gp2.wasJustPressed((GamepadKeys.Button.X))){
            intake.openGate();
            intake.setPower(intake_power);
        }
        if (gp2.wasJustPressed((GamepadKeys.Button.Y))){
            intake.closeGate();
            intake.setPower(0);
        }

        //can use this just to test the shooter
        if (gp2.wasJustPressed(GamepadKeys.Button.A)){
            Shooter.targetRPM += incr_decr_RPM;
        }
        if (gp2.wasJustPressed(GamepadKeys.Button.B)){
            Shooter.targetRPM -= incr_decr_RPM;
        }
        shooter.periodic();

        telemetry.addData("Intake Power", intake.intakeMotor.get());
        telemetry.addData("Intake Encoder Position", intake.intakeMotor.getCurrentPosition());

        telemetry.addData("TargetRPM: ", shooter.getTargetRPM());
        telemetry.addData("CurrentRPM: ", shooter.getRPM());
        telemetry.addData("At Speed: ", shooter.checkSpeed());
        telemetry.update();
    }
}
