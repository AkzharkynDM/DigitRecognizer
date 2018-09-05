import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import weka.core.converters.TextDirectoryLoader 
;public class ArffHandler {
	private String pathToLabels;
	private String pathToImages;
	
	private String relation;
	private String[]  attributes;
	private String[][] data;
	
	private int numberOfAttributes;
	private int numberOfRows;
	
	private File f = null;
	private String nameOfArff;
	
	public ArffHandler(String pathToLabels, String pathToImages, String nameOfArff) {
		this.pathToLabels=pathToLabels;
		this.pathToImages=pathToImages;
		this.nameOfArff=nameOfArff;
	}
	
	public void createArffFileFromMNIST() {
		  boolean fileWasCreated=false;
	     String pathToTxt="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/training_data.txt";
	      try {
	         f = new File(pathToTxt);
	         fileWasCreated=f.createNewFile();
	         if (!fileWasCreated) {
	        	 f.delete();
	        	 fileWasCreated=f.createNewFile();
	        	assert fileWasCreated;
	         }
	        
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      TextDirectoryLoader toArff=new TextDirectoryLoader();
	      try {
	    	assert f.exists();
	    	
			//toArff.setSource(f);
		//} catch (IOException e) {
	      } catch (Exception e) {
			e.printStackTrace();
		}
	     
	      MNISTFileHandler mnist=new MNISTFileHandler(pathToLabels, pathToImages);
			mnist.openFile();
			mnist.analyzeImage(); 
		
			assert mnist!=null;
			
	      retrieveInfoFromMNIST(mnist);
	      fillTheArff();
	      
	}
	
	public void removeArffFile() {
		f.delete();
	}
	
	private void retrieveInfoFromMNIST(MNISTFileHandler mnist) {
		assert mnist.getLoadedData()!=null;
		
		numberOfAttributes=mnist.getNumCols();
		numberOfRows=mnist.getNumRows();  
		
		assert numberOfAttributes!=0;
		assert numberOfRows!=0;
		
		//System.out.println(numberOfAttributes+" "+numberOfRows);
		double[][] dataInDouble=new double[numberOfRows][numberOfAttributes];
		assert dataInDouble!=null;
		
		for (int i=0; i<numberOfAttributes;i++) {
			//System.out.println(mnist.getLoadedData()[i].getData());
			dataInDouble[i]=mnist.getLoadedData()[i].getData();
			//System.out.println(dataInDouble[i]);
		}
		for (int i=0;i<numberOfRows;i++) {
			for (int j=0;j<numberOfAttributes;j++) {
				data[i][j]=String.valueOf(dataInDouble[i][j]);
			}
		}
	}
	
	private void fillTheArff() {
		PrintWriter writer=null;
		try {
			writer = new PrintWriter(nameOfArff);
			
			assert writer!=null;
			writer.println("@Relation");
			writer.println(relation);
			
			writer.println("@Attributes");
			for (int i=0; i<numberOfAttributes; i++) {
				writer.println(attributes[i]);
			}
			
			writer.println("@Data");
			for (int i=0; i<numberOfAttributes; i++) {
				for (int j=0; j<numberOfRows; j++) {
					writer.println(data[i][j]);
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
		writer.close();
		}
		System.out.println("The content of arff file was written");
		
	}
}
