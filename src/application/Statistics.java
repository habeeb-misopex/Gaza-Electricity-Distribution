package application;

public class Statistics {

	
	private float min=0;
	private float max=0;
	private float sum=0;
	private double avg=0;
	
	public Statistics() {
		
	}
	
	
	public Statistics(float min, float max, float sum, double avg) {

		this.min = min;
		this.max = max;
		this.sum = sum;
		this.avg = avg;
	}
	
	
	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "Statistics [min=" + min + ", max=" + max + ", sum=" + sum + ", avg=" + avg + "]";
	}

	
	
	
	
	
	

	
}
