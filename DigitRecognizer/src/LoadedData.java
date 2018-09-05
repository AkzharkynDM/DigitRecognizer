
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
		data=new double[size];
		for (int i=0;i<data.length;i++) {
			//System.out.println("size "+size+" value "+value+" i "+i);
			add(i, value);
		}
	}
	
	private void add(int index, double value) {
		this.data[index] += value;
	}
	
	public double[] getData() {
		return data;
	}
}
