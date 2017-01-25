public class MyTimer{

	private long start;
	private long end;
	
	public void startTime(){
		this.start = System.nanoTime();
	}

	public void endTime(){
		this.end = System.nanoTime();
	}	
	
	public long getStartTime(){
		return this.start;
	}
	
	public long getEndTime(){
		return this.end;
	}
	
	public long getTimeMeassure(){
		return this.end - this.start;
	}
}
