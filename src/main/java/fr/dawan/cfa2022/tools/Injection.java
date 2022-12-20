package fr.dawan.cfa2022.tools;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.Aspects14;
import org.springframework.beans.factory.annotation.Autowired;

class Strom {
	  protected double eyeRadius;
	  protected double eyePositionX;
	  protected double eyePositionY;
	  
	public Strom(double eyeRadius, double eyePositionX, double eyePositionY) {
		this.eyeRadius = eyeRadius;
		this.eyePositionX = eyePositionX;
		this.eyePositionY = eyePositionY;
	}
	public double getEyeRadius() {
		return eyeRadius;
	}

	public double getEyePositionX() {
		return eyePositionX;
	}

	public double getEyePositionY() {
		return eyePositionY;
	}
	  
  }
class RainStorm extends Strom {
	

  public RainStorm(double eyeRadius, double eyePositionX, double eyePositionY) {
		super(eyeRadius, eyePositionX, eyePositionY);
	}

public boolean isInEyeOfTheStorm(double positionX, double positionY) {
      double distance = Math.sqrt(Math.pow(positionX - eyePositionX, 2) +
                                  Math.pow(positionY - eyePositionY, 2));
      return distance < eyeRadius;
  }

  public double amountOfRain() {
      return eyeRadius * 20;
  }
  

}

class SnowStorm  extends Strom  {
  

  private double amountOfSnow;
  

  public SnowStorm(double eyeRadius, double eyePositionX, double eyePositionY ,double amountOfSnow) {
	super(eyeRadius, eyePositionX, eyePositionY);
	this.amountOfSnow = amountOfSnow;
}

	public double getAmountOfSnow() {
		return amountOfSnow;
	}
  
	public boolean isInEyeOfTheStorm(double positionX, double positionY) {
      double distance = Math.sqrt(Math.pow(positionX - eyePositionX, 2) +
                                  Math.pow(positionY - eyePositionY, 2));
      return distance < eyeRadius;
  }


}