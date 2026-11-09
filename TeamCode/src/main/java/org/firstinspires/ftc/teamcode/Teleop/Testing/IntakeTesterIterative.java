package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;

@TeleOp(name = "Intake Tester (Iterative)", group = "Testing")
public class IntakeTesterIterative extends OpMode {

    public Intake intake;
    private DcMotorEx intakeMotor = null;
    private Servo gateServo = null;
    GamepadEx gp1;


    public double intake_power = 1;
    @Override
    public void init() {
        this.intake = new Intake(this);
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
        gp1 = new GamepadEx(gamepad1);
        telemetry.addData("Status","Initialized. Press Play");
        telemetry.update();
    }

    @Override
    public void loop(){
        gp1.readButtons();
        if (gp1.isDown(GamepadKeys.Button.A)) {
            intake.setPower(intake_power);
        } else if (gp1.isDown(GamepadKeys.Button.B)){
            intake.setPower(-intake_power);
        } else if(gp1.wasJustPressed(GamepadKeys.Button.Y)){
            intake.closeGate();
        } else if(gp1.wasJustPressed(GamepadKeys.Button.X)) {
            intake.openGate();
        } else {
            intake.setPower(0);
        }
        telemetry.addData("Intake Power", intakeMotor.getPower());
        telemetry.addData("Intake Encoder Position", intakeMotor.getCurrentPosition());
        telemetry.update();
    }
}

