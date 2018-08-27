import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;

public class ImageTrainer {
	private String pathToLabels;
	private String pathToImages;
	
	public ImageTrainer(String pathToLabels, String pathToImages) {
		this.pathToLabels=pathToLabels;
		this.pathToImages=pathToImages;
	}
	public void trainLoadedImage(){
		MNISTFileHandler mnist=new MNISTFileHandler(pathToLabels, pathToImages);
		mnist.openFile();
		mnist.analyzeImage();
		
        Classifier j48tree = new J48();
        
        try {
        Instances train = new Instances(new BufferedReader(new FileReader
        		(pathToImages)));
        int lastIndex = train.numAttributes() - 1;
        
        train.setClassIndex(lastIndex);
        
        Instances test = new Instances(new BufferedReader(new FileReader
        		(pathToLabels)));
        test.setClassIndex(lastIndex);
        
        j48tree.buildClassifier(train);
        
        for(int i=0; i<test.numInstances(); i++) {
        
                double index = j48tree.classifyInstance(test.instance(i));
                String className = train.attribute(lastIndex).value((int)index);
                System.out.println(className);
        }
        } catch (Exception e) {
        	System.out.println("Problem with Classifier class: "+e.getMessage());
        	e.printStackTrace();
        }
}
}
