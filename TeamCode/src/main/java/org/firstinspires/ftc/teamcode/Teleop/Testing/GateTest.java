package org.firstinspires.ftc.teamcode.Teleop.Testing;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "gateTest", group = "Testing")
public class GateTest extends OpMode {

    public Servo gate;
     @Override
    public void init(){
         gate = hardwareMap.servo.get("gate");
         gate.setDirection(Servo.Direction.REVERSE);
         gate.setPosition(0.0);
         telemetry.addData("servo pos", gate.getPosition());
         telemetry.update();

     }

     @Override
     public void loop(){
         if (gamepad1.a){
             gate.setPosition(0.55);
             telemetry.addData("servo pos button A", gate.getPosition());
             telemetry.update();

         }  if (gamepad1.b){
             gate.setPosition(1.0);
             telemetry.addData("servo pos button B", gate.getPosition());
             telemetry.update();

         }  if (gamepad1.y){
             gate.setPosition(0.80);
             telemetry.addData("servo pos button Y", gate.getPosition());
             telemetry.update();

         }  if (gamepad1.x){
             gate.setPosition(1.1);
             telemetry.addData("servo pos button X", gate.getPosition());
             telemetry.update();
         }
//         else {
//             gate.setPosition(0.0);
//             telemetry.addData("servo pos no button", gate.getPosition());
//             telemetry.update();


        // }

     }

}
