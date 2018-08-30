import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ArffHandler {
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
	     
	      try {
	         f = new File(nameOfArff);
	         fileWasCreated=f.createNewFile();
	         assert fileWasCreated==true;
	        
	      } catch(Exception e) {
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
		numberOfAttributes=mnist.getNumCols();
		numberOfRows=mnist.getNumRows();  
		
		double[][] dataInDouble=new double[numberOfRows][numberOfAttributes];
		for (int i=0; i<numberOfAttributes;i++) {
			dataInDouble[i]=mnist.getLoadedData().getData();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		writer.close();
		}
	}
}
