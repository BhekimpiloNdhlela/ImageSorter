import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Bhekimpilo Ndhlela @18998712
 * @version 1.0
 * @since 2017-27-02
 */
public class CalculateMeanColor {
  private final String[] imageList;
  private final String imageDirectory;
  private final int numberOfImages;
  private Double []meanColorRed;
  private Double []meanColorGreen;
  private Double []meanColorBlue;

  /**
   * This is a construct for the object that calculates the mean of all the components of Red Green and Blue.
   * This Constructor receives an array of Strings which holds the name of the image(This is basically the
   * original image list which is read from the image path or directory) and a string which represents
   * the image directory or the image path.
   * @param  imageList
   * @throws IOException
   */
  public CalculateMeanColor(String[] imageList, String imageDirectory) throws IOException{
    this.imageDirectory = new String(imageDirectory);
    this.imageList      = imageList;
    this.numberOfImages = imageList.length;
    this.meanColorRed   = new Double[imageList.length];
    this.meanColorGreen = new Double[imageList.length];
    this.meanColorBlue  = new Double[imageList.length];
  }

  /**
   * This is the method that returns the number of all the images with-in a directory or path. It can also
   * be read as the length of the image list in the image directory.
   * @return numberOfImages
   */
  public int getNumberOfImages(){
    return numberOfImages;
  }

  /**
   * This is the method that gets an array of String which has all the images within a directory. This is
   * a list of all the images that are supposed to be processed and sorted according to their Red, Green and
   * Blue Mean Color Components. This is the original duplicate of the the image list that has not been
   * sorted yet.
   * @return ImageList
   */
  public String[] getListOfImages(){
    return imageList;
  }

  /**
   * This Method is a getter of all the Red Mean Components of all the pictures in the image directory or all
   * the images in the imageList. However, this list of all the image's Red Components is not in sorted order.
   * It preserves the order of the images in the image list directory. Meaning that every element in this array
   * corresponds to the index of the image that is of the imageList. for Example, imageList[2] has a Red mean
   * component of: meanColorRed[2];
   * @return imageRedMeanComponent
   */
  public Double[] getImageRedMeanComponent(){
    return meanColorRed;
  }

  /**
   * This Method is a getter of all the Green Mean Components of all the pictures in the image directory or all
   * the images in the imageList. However, this lisit of all the image's Green Components is not in sorted order.
   * It preserves the order of the images in the image list directory. Meaning that every element in this array
   * corresponds to the index of the image that is of the imageList. for Example, imageList[2] has a Green mean
   * component of: meanColorGreen[2];
   * @return imageGreenMeanComponent
   */
  public Double[] getImageGreenMeanComponent(){
    return meanColorGreen;
  }

  /**
   * This Method is a getter of all the Blue Mean Components of all the pictures in the image directory or all
   * the images in the imageList. However, this list of all the image's Blue Components is not in sorted order.
   * It preserves the order of the images in the image list directory. Meaning that every element in this array
   * corresponds to the index of the image that is of the imageList. for Example, imageList[2] has a Blue mean
   * component of: meanColorBlue[2];
   * @return imageBlueMeanComponent
   */
  public Double[] getImageBlueMeanComponent(){
    return meanColorBlue;
  }

  /**
   * This is a helper Method that helps calculate the mean of the Red Green and the Blue components of the given
   * image This method calculates the mean of one image which is in the form of a buffered image and returns a
   * double array which is of length of 3. This array includes the Red mean of the image in index[0], Green mean
   * of the image in index[1] and Blue mean of the image in index[2]
   * @param   bufferedImageAtHand
   * @return  imageColorComponents
   */
  private double[] imageColorMeanValues(BufferedImage bufferedImage){
    double redSigma   = 0.0;
    double greenSigma = 0.0;
    double blueSigma  = 0.0;
    int pixelCounter  = 0;
    int heightOfImage = bufferedImage.getHeight();
    int widthOfImage  = bufferedImage.getWidth();
    int pixel;
    for(int i = 0; i < heightOfImage; i++){
      for(int j = 0; j < widthOfImage; j++){
        pixel = bufferedImage.getRGB(j, i);
        redSigma     += (pixel >> 16) & 0xff;
        greenSigma   += (pixel >> 8) & 0xff;
        blueSigma    += (pixel) & 0xff;
        pixelCounter += 1;
      }
    } return new double[]{ redSigma / pixelCounter, greenSigma / pixelCounter, blueSigma / pixelCounter};
  }

  /**
   * This is the Method that Calculates the mean of all the color components. For example it calculates the Mean
   * of Red, Green and Blue by the aid of a helper method imageColorMeanValues.
   * @param  imageDirectory
   * @throws IOException
   */
  public void imageColorMeanValues() throws IOException{
    for(int i = 0; i < numberOfImages; i++ ){
      double []colorMean = imageColorMeanValues(ImageIO.read(new File(imageDirectory.concat(imageList[i]))));
      meanColorRed[i]    = colorMean[0];
      meanColorGreen[i]  = colorMean[1];
      meanColorBlue[i]   = colorMean[2];
    }
  }
}
