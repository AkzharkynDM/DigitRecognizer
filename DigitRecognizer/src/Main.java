import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//String pathToImage="./images/apple.jpg";
		
		//ImagePixelsGetter ipg=new ImagePixelsGetter(pathToImage);
		
		//System.out.println(ipg.convertTo2DUsingGetRGB());
		//System.out.println(ipg.convertTo2DWithoutUsingGetRGB());
		String nameOfArff="training_data.arff";
		String pathToLabels="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/train-images.idx3-ubyte";
		String pathToImages="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/train-images.idx3-ubyte";
		String pathToArff="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/"+nameOfArff;
		ImageTrainer it=new ImageTrainer();
		
		
		it.trainLoadedImage(pathToLabels, pathToImages, nameOfArff);
		it.classify(pathToArff);
		
	}

}
