import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MNISTFileHandler {
	private int numLabels;
	private int numImages;
	private int numRows;
	private int numCols;
	private String pathToLabels;
	private String pathToImages;

	private LoadedData data;
	private final static int sizeOfTestSet=2050;
	private final static int sizeOfTrainingSet=2050;
	private DataInputStream inputStream=null;
	private Map<LoadedData, LoadedData> pairs;
	
	public MNISTFileHandler(String pathToLabels, String pathToImages) {
		pairs=new HashMap<LoadedData, LoadedData>();
		this.pathToLabels=pathToLabels;
		this.pathToImages=pathToImages;
	}
	
	public void openFile() {
		try {
			DataInputStream labels = new DataInputStream(new FileInputStream(
					pathToLabels));
			DataInputStream images = new DataInputStream(new FileInputStream(
					pathToImages));
			int magicNumber = labels.readInt();
			assert magicNumber==sizeOfTrainingSet-1;
			
			magicNumber = images.readInt();
			assert magicNumber==sizeOfTrainingSet+1;
			
			this.numLabels = labels.readInt();
			this.numImages = images.readInt();
			this.numRows = images.readInt();
			this.numCols = images.readInt();
			
			assert numLabels == numImages;
			
			byte[] labelsData = new byte[numLabels];
			labels.read(labelsData);
			int imageVectorSize = numCols * numRows;
			byte[] imagesData = new byte[numLabels * imageVectorSize];
			images.read(imagesData);
			
			
			int imageIndex = 0;
			for(int i=0;i<this.numLabels;i++) {
				int label = labelsData[i];
				LoadedData inputData = new LoadedData(imageVectorSize);
				for(int j=0;j<imageVectorSize;j++) {
					inputData.setData(j, ((double)(imagesData[imageIndex++]&0xff))/255.0);
				}
				LoadedData idealData = new LoadedData(10, 1.0);
				idealData.setData(label, 1.0);
				pairs.put(inputData,idealData);
			}
			
			images.close();
			labels.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void analyzeImage() {
		
	}
	
	public int getNumLabels() {
		return numLabels;
	}
	public int getNumImages() {
		return numImages;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	public LoadedData getLoadedData() {
		return data;
	}
}
