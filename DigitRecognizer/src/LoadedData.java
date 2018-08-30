
public class LoadedData {
	private double[] data;
	private int size;
	private double value;
	
	public LoadedData(int size) {
	this.size=size;	
	}
	
	public LoadedData(int size, double value) {
		this.size=size;	
		this.value=value;
		}
	
	public void setData(double[] data) {
		this.data=data;
	}
	
	public void setData(double[] data, double value) {
		this.data=data;
		this.value=value;
	}
	
	public void setData(int size, double value) {
		this.size=size;
		this.value=value;
	}
	
	public void add(final int index, final double value) {
		this.data[index] += value;
	}
	
	public double[] getData() {
		return data;
	}
}
