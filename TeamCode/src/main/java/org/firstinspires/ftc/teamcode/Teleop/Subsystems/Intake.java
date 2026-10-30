package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Intake {
    //awesome intake stuff
    public final MotorEx intakeMotor;

    public static double power;
    //this robot is going to dance ehehehehehe
    public Servo dancingServo;
    public Servo gate;
    public Intake (OpMode opMode){
        intakeMotor = new MotorEx(opMode.hardwareMap, "intake", Motor.GoBILDA.BARE);
        dancingServo = opMode.hardwareMap.servo.get("dancingServo");
        gate = opMode.hardwareMap.servo.get("gate");

    }
    //tune these positions later
    public void raiseTheRoof() {dancingServo.setPosition(0);}

    public void limbo(){dancingServo.setPosition(0);}
    public void getOnTheDanceFloor(){dancingServo.setPosition(0);}
    public void openGate() {gate.setPosition(0);}
    public void closeGate() {gate.setPosition(0);}
    public void setPower(double power){intakeMotor.set(power);}

    public void reverseIntake(double power){intakeMotor.set(-power);}

    public void stopIntake(){intakeMotor.set(0);}

}
