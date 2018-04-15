import java.io.File;
import java.io.IOException;

/**
 * @author Bhekimpilo Ndhlela
 * @author 18998712
 * @versin 1.0
 * @since  2017-27-02
 */
public class SorterInitializer {
  private final String imageDirectory;
  private final int algorithChoice;
  private final int meanColorUsed;
  private final int guiLauncher;
  private final boolean isGuiOn;
  private String[] imageList;

  /**
   * @param imgDirectory	The is the directory of the images
   * @param algorithmChoice	This is the algorithm of choice to be used for sorting
   * @param meanColorUsed	The mean color to be used while sorting the images
   * @param guiLauncher 	This determines if the GUI state is ON or OFF
   */
  public SorterInitializer(String imageDirectory, int algorithmChoice, int meanColorUsed, int guiLauncher) throws IOException{
    this.imageDirectory = imageDirectory;
    this.algorithChoice = algorithmChoice;
    this.meanColorUsed  = meanColorUsed;
    this.guiLauncher    = guiLauncher;
    this.isGuiOn        = setGuiState();
  }

  /**
   * This Method sets the image list that is to be sorted by the program. It uses the image names as the elements
   * of the array This array is meant to be sorted concurrently with the color array components that contain the
   * the mean R, G or B components Of the images.
   */
  private void setImageList(){
    String path = new String(imageDirectory);
    File file   = new File(path);
    imageList   = file.list();
  }

  /**
   * @return imageList
   */
  public String[] getImageList(){
    setImageList();
    return imageList;
  }

  /**
   * This is a helper method that Sets The GUI state which is a boolean value.
   * For Example: 0 = FALSE(GUI State is OFF) & 1 = TRUE(GUI STATE IS ON).
   */
  private boolean setGuiState(){
    return (guiLauncher == 1);
  }

  /**
   * This method is for getting The GUI state which is a boolean value. For Example: 0 = FALSE(GUI State is OFF) & 1 = TRUE(GUI STATE IS ON).
   * @return isGuiOn?
   */
  public boolean isGuiStateOn(){
    return isGuiOn;
  }

  /**
   * This Method is for getting the sort color. The sort color is the color that is to be used to sort the images in the working directory.
   * @return colorUsedToSort
   */
  public int getSortColor(){
    return meanColorUsed;
  }

  /**
   * This Method Returns the image Directory or the image path in a String or URL format.
   * @return imageDirectory.
   */
  public String getImageDirectory(){
    return imageDirectory;
  }

  /**
   * This Method is for getting the sort sorting algorithm. The sort algorithm is the algorithm that is to be used to sort the images in the
   * working directory.
   * @return algorithmUsedToSort
   */
  public int getSortAlgorithm(){
    return algorithChoice;
  }

  /**
   *String representation of my imageSortInit object
   */
  public String toString(){
    return imageDirectory + " "+ algorithChoice + " "+ meanColorUsed + " " + isGuiOn; 
  }
}
