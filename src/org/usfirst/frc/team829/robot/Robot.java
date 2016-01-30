
package org.usfirst.frc.team829.robot;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	Drive drive;																		// Object for the drive and transmission
	
	Shooter shooter;																	// Object for the shooter
	
	Compressor compressor;																// Object for the compressor
	
	Joystick dual, leftStick, rightStick;												// Controls objects
	
	//CameraServer camera;
	
	int session;
	
	VisionHelper visionHelper;
	
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(100, 155);						//Default hue range for yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(67, 255);						//Default saturation range for yellow tote
	NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(200, 255);						//Default value range for yellow tote
	
	/*double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area

	Image frame;
	Image binaryFrame;
	int imaqError;
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);*/
    
	   public void robotInit() {
        
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        dual = new Joystick(2);			
        
        compressor.start();
        
        drive = new Drive();
        
        shooter = new Shooter();
        
        /*camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture();
        */

        /*visionHelper = new VisionHelper();
        visionHelper.setHueRange(100, 155);
        visionHelper.setSatRange(min, max);
        */
        // the camera name (ex "cam0") can be found through the roborio web interface
        /*session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0); 
		
		//Put default values to SmartDashboard so fields will appear
		SmartDashboard.putNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
		SmartDashboard.putNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
		SmartDashboard.putNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
		SmartDashboard.putNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
		SmartDashboard.putNumber("Tote val min", TOTE_VAL_RANGE.minValue);
		SmartDashboard.putNumber("Tote val max", TOTE_VAL_RANGE.maxValue);
		SmartDashboard.putNumber("Area min %", AREA_MINIMUM);*/
    }
    
	
    public void autonomousInit() {
    	
    }

    
    public void autonomousPeriodic() {
    	
    }

    
    public void teleopInit(){
    	//NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        
    }
    
    public void teleopPeriodic() {
    	
    	SmartDashboard.putBoolean("Fire Button", dual.getRawButton(2));	
    	
    	if(dual.getRawButton(3))
    		drive.transmissionPressed();
    	drive.update(-leftStick.getY(), -rightStick.getY());
    	
    	if(dual.getRawButton(2))
    		shooter.shootPressed();
    	shooter.update();
    	
    	
    	/*NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
    	NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        //CameraServer.getInstance().setImage(frame);
    	
    	
    	//Update threshold values from SmartDashboard. For performance reasons it is recommended to remove this after calibration is finished.
		TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
		TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
		TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
		TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
		TOTE_VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Tote val min", TOTE_VAL_RANGE.minValue);
		TOTE_VAL_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote val max", TOTE_VAL_RANGE.maxValue);

		//Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_VAL_RANGE);

		criteria[0].lower = (float)AREA_MINIMUM;
		//imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
		//Send particle count to dashboard
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Masked particles", numParticles);
		CameraServer.getInstance().setImage(binaryFrame);*/
    }
    
    
    public void testPeriodic() {
    
    }
    
    public void disabledInit(){
    	//NIVision.IMAQdxStopAcquisition(session);
    }
    
}
