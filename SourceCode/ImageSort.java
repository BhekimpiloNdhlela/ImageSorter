import java.io.IOException;

/**
 * This is the main client library of the project or of the program. This is
 * where the sorting of the images is initialized or triggered in two different
 * ways. one way the image list is to be sorted while the Graphical User Interface
 * is in OFF State and the other way is when the Graphical User Interface is ON
 * State. If the GUI state is ON then the GUI will be triggered and the sorting
 * will take place in the in the PROGRAM GUI CLASS. or if the GUI state is OFF then
 * the sorting starts at the sorter initializer.
 * @author Bhekimpilo Ndhlela
 * @author 18998712
 * @version 1.0
 * @since 2017-27-02
 */
public class ImageSort {
  private static String[] inputData = new String[3]; //data that goes to the GUI sorter Class

  /**
   * @param  args
   * @throws NumberFormatException
   * @throws IOException
   */
  public static void main(String[] args) throws NumberFormatException, IOException {
    inputData = args;
    String imagePath = new String(args[0]);
    int algorithmChoice = Integer.parseInt(args[1]);
    int sortColor = Integer.parseInt(args[2]);
    int guiState = Integer.parseInt(args[3]);

    switch(guiState){
      case 0: //This is the case when the GUI state is not to be launched.
        triggerOffGuiState(imagePath, algorithmChoice, sortColor, guiState);
        break;
      case 1: //This is the case when the GUI state is to be launched.
        trigerGUI();
        break;
      default://This is the case when the GUI state is not equals to  0 or 1.
        System.out.println("Invalid Input, Check your Input.");
        System.exit(0);
        break;
    }
  }

  /**
   * This is a static method that is used for transferring the input data from
   * the main client to the JFrame class or to the Graphical User Interface.
   * These values include argument values that are parameters of the program.
   * For example the color to be used, the algorithm to be used for sorting and
   * the path of the images.
   * @return inputData
   */
  public static String[] passInputToFormClass() {
    return inputData;
  }

  /**
   * Method used to trigger or open the off state GRAPHICAL USER INTERFACE Mode of the Sorter.
   * @param imagePath        image directory
   * @param algorithmChoice  sorting algorithm of choice
   * @param sortColor        sorting color of choice
   * @param guiState         graphical user interface state
   * @throws IOException	   Number Format and File Not Found exception
   */
  private static void triggerOffGuiState(String imagePath, int algorithmChoice, int sortColor, int guiState) throws IOException{
    SorterInitializer imageSortertInit = new SorterInitializer(imagePath, algorithmChoice, sortColor, guiState);
    CalculateMeanColor meanColorCalculator = new CalculateMeanColor(imageSortertInit.getImageList(), imagePath);
    meanColorCalculator.imageColorMeanValues();
    SorterUnit proccessingUnit = new SorterUnit(meanColorCalculator);
    proccessingUnit.initiateImageSorter(algorithmChoice, sortColor);
    System.exit(0);
  }

  /**
   * Method used to trigger or open the GRAPHICAL USER INTERFACE Mode of the Sorter. [Comment out for uni testing]
   */
  private static void trigerGUI(){
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
        if("Nimbus".equals(info.getName()))  {javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; }
      }catch (ClassNotFoundException ex) {java.util.logging.Logger.getLogger(ProgramGUI.
        class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }catch (InstantiationException ex) {java.util.logging.Logger.getLogger(ProgramGUI.
        class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }catch (IllegalAccessException ex) {java.util.logging.Logger.getLogger(ProgramGUI.
        class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }catch (javax.swing.UnsupportedLookAndFeelException ex) {java.util.logging.Logger.
        getLogger(ProgramGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);}
      //Create and Display the Sorter GUI
      java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        try { new ProgramGUI().setVisible(true);
      }catch (NumberFormatException | IOException e) {
        e.printStackTrace();
      }}});
  }
}
