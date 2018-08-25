import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MNISTFileHandler {
	private String path="/Users/akzharkynduisembiyeva/Downloads/train-images.idx3-ubyte";
	private File file;
	private TrainingSetLabelFile labelFile;
	private final static int sizeOfTestSet=5000;
	private final static int sizeOfTrainingSet=5000;
	
	public void MNISTFileHandler() {
		labelFile=new TrainingSetLabelFile();
		
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported in this platfrom");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists())
			try {
				desktop.open(file);
			} catch (IOException e) {
				System.out.println("Problem with Desktop class"+e.getMessage());
				e.printStackTrace();
			}
        
       
        file = new File(path);
        if(file.exists())
			try {
				desktop.open(file);
			} catch (IOException e) {
				System.out.println("Problem with File class"+e.getMessage());
				e.printStackTrace();
			}
    
	}
	
	public void analyzeImage() {
		for (int i=0;i<sizeOfTrainingSet;i++) {
			
		}
	}
}
