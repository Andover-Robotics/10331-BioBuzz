package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "Intake Tester (Iterative)", group = "Testing")
public class IntakeTesterIterative extends OpMode {
    private DcMotorEx intakeMotor = null;
    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
        telemetry.addData("Status","Initialized. Press Play");
        telemetry.update();

    }
    @Override
    public void loop(){
        if (gamepad1.a) {
            intakeMotor.setPower(1.0);
        } else if (gamepad1.b) {
            intakeMotor.setPower(-1.0);
        } else {
            intakeMotor.setPower(0.0);
        }
        telemetry.addData("Intake Power", intakeMotor.getPower());
        telemetry.addData("Intake Encoder Position", intakeMotor.getCurrentPosition());
        telemetry.update();
    }
}

