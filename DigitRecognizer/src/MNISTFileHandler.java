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
			assert magicNumber==sizeOfTrainingSet+1;
			
			magicNumber = images.readInt();
			
			this.numLabels = labels.readInt();
			this.numImages = images.readInt();
			this.numRows = images.readInt();
			this.numCols = images.readInt();
			
			assert numLabels == numImages;
			
			byte[] labelsData = new byte[numLabels];
			labels.read(labelsData);
			//for (int i=0;i<labelsData.length;i++) {
			//System.out.println("labels data"+labelsData[i]);
			//}
			int imageVectorSize = numCols * numRows;
			byte[] imagesData = new byte[numLabels * imageVectorSize];
			images.read(imagesData);
			//for (int i=0;i<imagesData.length;i++) {
			//System.out.println("Images data"+imagesData[i]);
			//}
			int imageIndex = 0;
			for(int i=0;i<this.numLabels;i++) {
				int label = labelsData[i];
				LoadedData inputData = new LoadedData(imageVectorSize);
				//for(int j=0;j<imageVectorSize;j++) {
				//	inputData.setData(j, ((double)(imagesData[imageIndex++]&0xff))/255.0);
				//}
				if (label<0) continue;
				inputData.setData(i, ((double)(imagesData[imageIndex++]&0xff))/255.0);
				
				//System.out.println(inputData.getData());
				LoadedData idealData = new LoadedData(10, 1.0);
				idealData.setData(label, 1.0);
				System.out.println("content from ideal data "+idealData.getData());
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
	public LoadedData[] getLoadedData() {
		int size=pairs.keySet().size();
		assert size!=0;
		//System.out.println("size of mnist"+size);
		LoadedData[] loadedDataToReturn=new LoadedData[size];
		for (int i=0;i<size/1000;i++) {
			loadedDataToReturn[i]=(LoadedData) pairs.keySet().toArray()[i];
			//System.out.println(loadedDataToReturn[i].getData());
		}
		return loadedDataToReturn;
	}
}
