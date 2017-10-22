package help_data;

public class Time {

	boolean paused = true;
	int speed = 1;
	long time = 0;
	
	public Time(){
		while (paused){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000* speed);
			time++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void pause(boolean pause){
		paused = pause;
	}
	
	public int incSpeed(){
		if(speed <10){
			return ++speed;
		}
		return speed;
	}
	
	public int decSpeed(){
		if(speed >0){
			return --speed;
		}
		return speed;
	}
	
}
