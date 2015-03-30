public class Airplane {
	private int flightNo = 0;
    private int waitTime = 0;

    public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public void incWaitTime() {
		this.waitTime++;
	}
}
