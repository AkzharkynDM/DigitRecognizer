import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String path="/images/apple.jpg";
		
		ImagePixelsGetter ipg=new ImagePixelsGetter(path);
		
		System.out.println(ipg.convertTo2DUsingGetRGB());
		System.out.println(ipg.convertTo2DWithoutUsingGetRGB());

		ImageTrainer it=new ImageTrainer(path);
		it.trainLoadedImage();
	}

}
