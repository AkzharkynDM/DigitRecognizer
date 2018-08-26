import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MNISTFileHandler {
	private String path;
	
	private TrainingSetLabelFile labelFile;
	private final static int sizeOfTestSet=5000;
	private final static int sizeOfTrainingSet=5000;
	private DataInputStream inputStream=null;
	
	public MNISTFileHandler(String path) {
		labelFile=new TrainingSetLabelFile();
		this.path=path;
	}
	
	public void openFile() {
	try {
		inputStream = new DataInputStream(
				
				new FileInputStream(path));
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
	try {
    int labelMagicNumber = inputStream.readInt();
    int numberOfLabels = inputStream.readInt();

    System.out.println("labels magic number is: " + labelMagicNumber);
    System.out.println("number of labels is: " + numberOfLabels);
 
   
		inputStream.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
	}
	
	public void analyzeImage() {
		try {
				for (int i=0;i<sizeOfTrainingSet;i++) {
				labelFile.setOffset((byte) inputStream.readUnsignedByte());
			} 
				}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		
	}
}
