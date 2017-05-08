import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * @author Bhekimpilo Ndhlela @18998712
 * @version 1.0
 * @since 2017-27-02
 */
public class SorterUnit {
  private String[]originalImageList;
  private String []sortingList;
  private final int numberOfImages;
  private Double []meanColorRed;
  private Double []meanColorGreen;
  private Double []meanColorBlue;
  private double startingTime = 0;
  private double  terminatingTime = 0;
  private final CalculateMeanColor meanColorCalculator;

  /**
   * This is the constructor of this object it initializes the instance variables using the fields or values
   * from the mean calculator object.
   * @param meanColorCalculator
   */
  public SorterUnit(CalculateMeanColor meanColorCalculator){
    this.meanColorCalculator = meanColorCalculator;
    this.originalImageList = meanColorCalculator.getListOfImages();
    this.numberOfImages = meanColorCalculator.getNumberOfImages();
    this.meanColorRed = new Double[numberOfImages];
    this.meanColorGreen = new Double[numberOfImages];
    this.meanColorBlue = new Double[numberOfImages];
    makeDefensiveCopy();
  }

  /**
   * Sort the image list using insertion sort.
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  private void insertionSort(Double[] colorToBeUsed) throws FileNotFoundException{
    startingTime = System.currentTimeMillis();
    Insertion.sort(colorToBeUsed, sortingList);
    terminatingTime = System.currentTimeMillis();
    writeToTextFile(colorToBeUsed);
  }

  /**
   * Sort the image list using Shell sort.
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  private void shellSort(Double[] colorToBeUsed) throws FileNotFoundException{
    startingTime = System.currentTimeMillis();
    Shell.sort(colorToBeUsed, sortingList);
    terminatingTime = System.currentTimeMillis();
    writeToTextFile(colorToBeUsed);
  }

  /**
   * Set the image list using Merge Sort.
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  private void mergeSort(Double[] colorToBeUsed) throws FileNotFoundException{
    startingTime = System.currentTimeMillis();
    Merge.sort(colorToBeUsed, sortingList);
    terminatingTime = System.currentTimeMillis();
    writeToTextFile(colorToBeUsed);
  }

  /**
   * Set the image list using Quick Sort.
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  private void quickSort(Double[] colorToBeUsed) throws FileNotFoundException{
    startingTime = System.currentTimeMillis();
    Quick.sort(colorToBeUsed, sortingList);
    terminatingTime = System.currentTimeMillis();
    writeToTextFile(colorToBeUsed);
  }

  /**
   * Sort the image list using Selection Sort
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  private void selectionSort(Double[] colorToBeUsed) throws FileNotFoundException{
    startingTime = System.currentTimeMillis();
    Selection.sort(colorToBeUsed, sortingList);
    terminatingTime = System.currentTimeMillis();
    writeToTextFile(colorToBeUsed);
  }

  /**
   * This method is a getter method for getting the elapsed time to sort the imageList.
   * @return elapsedTime
   */
  public String getTimeTakenToSort(){
    return "" + ((terminatingTime - startingTime) / 1000.0);
  }

  /**
   * This Method is responsible for getting or acquiring the original copy of the list of images,
   * original meaning that the list contains image file names that are not sorted.
   * @return originalImageList
   */
  public String[] getOriginalImageList(){
    return originalImageList;
  }

  /**
   * This Method is responsible for getting or acquiring the sorted copy of the list of images,
   * sorted meaning that the list contains image file names that are already sorted in ascending order..
   * @return sortedImageList
   */
  public String[] getSortedList(){
    return sortingList;
  }

  /**
   * This is a helper Method that writes the output final text file that represents Strings of
   * image list according to the ascending order of the component used to sort the images. the
   * Image on the top has the lowest mean component of the R or G or B value that was used to
   * sort the image list.
   * @param  colorToBeUsed
   * @throws FileNotFoundException
   */
  public void writeToTextFile(Double []colorToBeUsed) throws FileNotFoundException{
    String outPutTextFile = new String("sorted.txt");
    PrintWriter outPutStream = new PrintWriter(outPutTextFile);
    for(int i = 0; i < numberOfImages; i++){
      outPutStream.println(sortingList[i]);
      outPutStream.flush(); //flushes all the streams of data (image names in this case) from the buffer to the text file or out streams.
    } outPutStream.close(); //Close the Text File.
  }

  /**
   * This Method is for implementing defensive copy. This method makes the copy of an array that is to be sorted
   * with the original duplicate of the original array of the images and the color sorting components..
   */
  private void makeDefensiveCopy(){
    this.sortingList = new String[numberOfImages];
    Double meanRed[] = meanColorCalculator.getImageRedMeanComponent();
    Double meanGreen[] = meanColorCalculator.getImageGreenMeanComponent();
    Double meanBlue[] = meanColorCalculator.getImageBlueMeanComponent();

    for(int i = 0; i < numberOfImages; i++){
      sortingList[i] = originalImageList[i];    //Defensive Copy for the image list
      meanColorRed[i] = meanRed[i];             //Defensive Copy for the mean red color component
      meanColorGreen[i] = meanGreen[i];         //Defensive Copy for the mean green color component
      meanColorBlue[i] = meanBlue[i];           //Defensive Copy for the mean blue color component
    }
  }

  /**
   * This method returns the array in the form of a Double wrapper type because it implements the
   * Comparable interface.
   * @param  sortingMeanColor
   * @return componentMeanColorToBeSorted
   */
  private Double[] initiateImageSorter(int sortingColor){
    switch(sortingColor){
      case 0://The Array of the RED Mean Color Component.
        return meanColorRed;
      case 1://The Array of the GREEN Mean Color Component.
        return meanColorGreen;
      case 2://The Array of the BLUE Mean Color Component.
        return meanColorBlue;
      default://Called when the sorting color is out of range.
        System.out.println("Invalid Input for color choices, Check Input.");
        System.exit(0);//comment out for covarage testing
        break;
    } return new Double[0]; //This case is unreachable/ unreachable statement.
  }

  /**
   * This Method initializes the image sorter. It sorts the the images according to the color that was
   * specified as sorting color, it also chooses which algorithm to use for the sorting.
   * @param  sortingAlgorithm
   * @param  sortingColor
   * @throws FileNotFoundException
   */
  public void initiateImageSorter(int sortingAlgorithm, int sortingColor) throws FileNotFoundException{
    switch(sortingAlgorithm){
      case 0://Sort Image List using Insertion Sort
        insertionSort(initiateImageSorter(sortingColor));
        break;
      case 1://Sort Image List using Shell Sort
        shellSort(initiateImageSorter(sortingColor));
        break;
      case 2://Sort Image List using Merge Sort
        mergeSort(initiateImageSorter(sortingColor));
        break;
      case 3://Sort Image List using Quick Sort
        quickSort(initiateImageSorter(sortingColor));
        break;
      case 4://Sort Image List using Selection Sort
        selectionSort(initiateImageSorter(sortingColor));
        break;
      default://Called when the sorting algorithm is not in range
        System.out.println("Invalid Input for algorithm choice, Check Input.");
        System.exit(0);//comment out for covarage testing
        break;
    }
  }
}
