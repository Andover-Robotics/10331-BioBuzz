package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;

    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("fl");
        c.frontRightName.set("fr");
        c.backLeftName.set("bl");
        c.backRightName.set("br");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-3.201240179106945);
        c.yPodOffset.set(-0.8595545460858683);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.5916672967568103);
                Controller secondaryTranslationalForward = Controller.proportional(0.21860525651030174);
                Controller primaryTranslationalLateral = Controller.proportional(0.7375511603377599);
                Controller secondaryTranslationalLateral = Controller.proportional(0.27250544601483556);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.018611237368758672));
                c.brake.set(Controller.proportionalFeedforward(0.01581955176344487));

                c.headingFeedback.set(Controller.proportional(5.394031477587587));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.051730157992518226, 0.006692907054824237));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07775073757297912, 0.047548099433843756));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0010069953913944504, 0.0018803927756648874));

                c.maxAchievableForwardVelocity.set(52.82434953159532);
                c.maxAchievableStrafeVelocity.set(43.646255203548534);
                c.naturalForwardDeceleration.set(66.26870169282);
                c.naturalStrafeDeceleration.set(81.4757781563561);
            }
    );
}