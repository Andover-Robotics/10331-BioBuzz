package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Config
public class Intake {
    //awesome intake stuff
    public final MotorEx intakeMotor;

    public Intake (OpMode opMode){
        intakeMotor = new MotorEx(opMode.hardwareMap, "intake", Motor.GoBILDA.BARE);


    }
    public void setPower(double power){intakeMotor.set(power);}

    public void stopIntake(){intakeMotor.set(0);}

}
