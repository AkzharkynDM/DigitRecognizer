import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//String pathToImage="./images/apple.jpg";
		
		//ImagePixelsGetter ipg=new ImagePixelsGetter(pathToImage);
		
		//System.out.println(ipg.convertTo2DUsingGetRGB());
		//System.out.println(ipg.convertTo2DWithoutUsingGetRGB());

		String pathToLabels="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/train-images.idx3-ubyte";
		String pathToImages="/Users/akzharkynduisembiyeva/git/DigitRecognizer/training_set/train-images.idx3-ubyte";
		
		ImageTrainer it=new ImageTrainer(pathToLabels, pathToImages);
		it.trainLoadedImage();
	}

}
