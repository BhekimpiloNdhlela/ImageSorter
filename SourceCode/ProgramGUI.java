import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Bhekimpilo Ndhlela @18998712
 * @version 1.0
 * @since 2017-27-02
 */
public class ProgramGUI extends javax.swing.JFrame {
  private int currentImage = 0;
  private String[] sortedImages;
  private int numberOfImages;
  private String imageDirectory;
  private SorterInitializer imageSortertInit;
  private CalculateMeanColor meanColorCalculator;
  private SorterUnit imageSorter;

  /**
   * Creates new form ProgramForm by calling the Graphical User interface components and initializing the element of the form. It also
   * calls the method that is responsible for taking over from the client code which is the method that is responsible for initializing
   * the sorter in the Graphical User Interface mode
   * @throws IOException
   * @throws NumberFormatException
   */
  public ProgramGUI() throws NumberFormatException, IOException {
    initComponents();
    initialiseSorter();
  }

  /**
   * This method is for resorting images using the parameters that will be determined by the radio buttons for both the color to use
   * and the algorithm to use while sorting. This method enables buttons that are mean to be used and it disables the buttons that are
   * not meant to be used during this session of the program execution, for example it is impossible to click the next button when the
   * resort button has been clicked, it is also impossible to click on the resort button once it is clicked. it enable the buttons in
   * the resort menu.
   * @param evt
   */
  private void resortImagesButtonActionPerformed(java.awt.event.ActionEvent evt) {
    resortImagesButton.setEnabled(false);
    jButton4.setEnabled(true);
    redRadioButton.setEnabled(true);
    blueRadioButton.setEnabled(true);
    shellSortRadioButton.setEnabled(true);
    greenRadioButton.setEnabled(true);
    insertionSortButton.setEnabled(true);
    selectionSortRadioButton.setEnabled(true);
    mergeSortRadioButton.setEnabled(true);
    selectionSortRadioButton.setEnabled(true);
    quickSortRadioButton.setEnabled(true);
    firstButton.setEnabled(false);
    previousButton.setEnabled(false);
    lastButton.setEnabled(false);
    nextButton.setEnabled(false);
  }

  /**
   * This button goes to the last image, The image with the greatest value. For example image number length - 1, in the list of
   * all the images that have been sorted according to the ascending order of the color used to sort the images.
   * @param evt
   */
  private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {
    currentImage = numberOfImages - 1;
    lastButton.setEnabled(false);
    nextButton.setEnabled(false);
    previousButton.setEnabled(true);
    firstButton.setEnabled(true);
    displayImage(currentImage);
  }

  /**
   * This button goes to the first image, The image with the lowest value. for example image number 0, in the list of all the images
   * that have been sorted according to the ascending order of the color used to sort the images.
   * @param evt
   */
  private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {
    currentImage = 0;
    previousButton.setEnabled(false);
    firstButton.setEnabled(false);
    nextButton.setEnabled(true);
    lastButton.setEnabled(true);
    displayImage(currentImage);
  }

  /**
   * This goes to the next image in the image list. It does this by incrementing the current image and checking if whether the
   * current image is in range else if not it does not increment the current image value because it will be out of range.
   * @param evt
   */
  private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    if(currentImage < numberOfImages - 1){
      currentImage = currentImage + 1;
      previousButton.setEnabled(true);
      firstButton.setEnabled(true);
    }
    if(currentImage == numberOfImages - 1){
      nextButton.setEnabled(false);
      lastButton.setEnabled(false);
    }displayImage(currentImage);
  }

  /**
   * This goes to the previous image in the image list. It does this by decrementing the current image and checking if whether
   * the current image is in range else if not it does not decrement the current image value because it will be out of range.
   * @param evt
   */
  private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
    if(currentImage > 0){
      currentImage = currentImage - 1;
      lastButton.setEnabled(true);
      nextButton.setEnabled(true);
    }
    if(currentImage == 0){
      previousButton.setEnabled(false);
      firstButton.setEnabled(false);
    }displayImage(currentImage);
  }

  /**
   * This method is responsible for re sorting the list of images using the different parameters that are selected but the user
   * of the program or sorter by using the algorithm sorter radio button to select the desired algorithm for sorting the images
   * and the mean color to use to sort the list of images. This button also disable the components of the Resorting menu or panel
   * temporarily because the sort and display button displays the new list of sorted images according to the parameters selected
   * from this menu.
   * @param evt
   * @throws FileNotFoundException
   */
  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {
    jButton4.setEnabled(false);
    redRadioButton.setEnabled(false);
    blueRadioButton.setEnabled(false);
    greenRadioButton.setEnabled(false);
    insertionSortButton.setEnabled(false);
    selectionSortRadioButton.setEnabled(false);
    shellSortRadioButton.setEnabled(false);
    mergeSortRadioButton.setEnabled(false);
    selectionSortRadioButton.setEnabled(false);
    quickSortRadioButton.setEnabled(false);
    firstButton.setEnabled(true);
    previousButton.setEnabled(true);
    lastButton.setEnabled(true);
    nextButton.setEnabled(true);
    resortImagesButton.setEnabled(true);

    int detailsOfGuiSort[] = new int[2];

    //algorithms used for a new sort
    if(insertionSortButton.isSelected())           { detailsOfGuiSort[0] = 0; }
    else if(shellSortRadioButton.isSelected())     { detailsOfGuiSort[0] = 1; }
    else if(mergeSortRadioButton.isSelected())     { detailsOfGuiSort[0] = 2; }
    else if(quickSortRadioButton.isSelected())     { detailsOfGuiSort[0] = 3; }
    else if(selectionSortRadioButton.isSelected()) { detailsOfGuiSort[0] = 4; }
    //colors used for new sort
    if(redRadioButton.isSelected())                { detailsOfGuiSort[1] = 0; }
    else if(greenRadioButton.isSelected())         { detailsOfGuiSort[1] = 1; }
    else if(blueRadioButton.isSelected())          {  detailsOfGuiSort[1] = 2; }
    guiNewSortTrigger(detailsOfGuiSort);
  }

  /**
   * This is the method that takes over after the initialiseSorter. This method is called after the resort button has been clicked.
   * This is the active method b=during the image sort when the user start uses the Graphical User Interface. It performs the necessary
   * operations to sort the array of the images using the specified color by the radioButton radio group and the algorithm of choice
   * which is also determined by the radio button group.
   * @param detailsOfGuiSort
   * @throws FileNotFoundException
   */
  private void guiNewSortTrigger(int []detailsOfGuiSort) throws FileNotFoundException{
    firstButton.setEnabled(false);
    previousButton.setEnabled(false);
    imageSorter  = new SorterUnit(meanColorCalculator);
    currentImage = 0;
    imageSorter.initiateImageSorter(detailsOfGuiSort[0], detailsOfGuiSort[1]);
    sortedImages = imageSorter.getSortedList();

    TimeDisplayer.setText(imageSorter.getTimeTakenToSort());
    displayImage(currentImage);
  }

  /**
   * This method is for displaying the current image which is the position at currently show which is the index of the image
   * in the array that has sorted images. For example if currently show is equals to(=) 10 then the image at index 10 of the
   * sorted array is going to be displayed in the image canvas.
   * @param currentlyShow image index to display of the image canvas
   */
  private void displayImage(int currentlyShow){
    String showingImage = new String(sortedImages[currentlyShow]);
    imageSlideCanvas.setIcon(null);
    ImageIcon icon  = new ImageIcon(imageDirectory.concat((showingImage)));
    Image image     = icon.getImage().getScaledInstance(imageSlideCanvas.getWidth(),imageSlideCanvas.getHeight(), Image.SCALE_SMOOTH);
    imageSlideCanvas.setIcon(new ImageIcon(image));
  }

  /**
   * This is the Major method of the Graphical User Interface sorter. This is the method that initializes the sorter from the main
   * method thats if the GUI is triggered this method is called so that the sorting process starts at this point. This method declare
   * and initializes all the necessary objects for the sorting for example the object for calculation the mean of the images, the
   * sorter initializer is created by this method so as to start with the sorting of the images.
   * @throws NumberFormatException
   * @throws IOException
   */
  private void initialiseSorter() throws NumberFormatException, IOException{
    DataForTheGUI dataForGui = new DataForTheGUI();
    imageDirectory           = new String(dataForGui.getDataForGui()[0]);
    int algorithimChoice     = Integer.parseInt(dataForGui.getDataForGui()[1]);
    int colorChoice          = Integer.parseInt(dataForGui.getDataForGui()[2]);
    int guiState             = Integer.parseInt(dataForGui.getDataForGui()[3]);

    imageSortertInit    = new SorterInitializer(imageDirectory, algorithimChoice, colorChoice, guiState);
    meanColorCalculator = new CalculateMeanColor(imageSortertInit.getImageList(), imageSortertInit.getImageDirectory());
    meanColorCalculator.imageColorMeanValues();
    imageSorter = new SorterUnit(meanColorCalculator);

    imageSorter.initiateImageSorter(algorithimChoice, colorChoice);
    sortedImages   = imageSorter.getSortedList();
    numberOfImages = meanColorCalculator.getNumberOfImages();
    firstButton.setEnabled(false);
    previousButton.setEnabled(false);
    TimeDisplayer.setText(imageSorter.getTimeTakenToSort());
    displayImage(0);
  }

  /**
   *@author Bhekimpilo Ndhlela @ 18998712
   *This is a helper class that is used by the Graphical User Interface mode to acquire or get the information of the Initial sorting
   *parameters from the main class or client class(ImageSort) for example this Helper class gets an array of length 3 whereby getDataForGUI
   *is an array of string type that contains: the path or directory of the images, the mean color to be used for the initial sorting and the
   *algorithm to be used for the initial sort.
   */
  private class DataForTheGUI {
    public String[] getDataForGui(){
      return ImageSort.passInputToFormClass();
    }
  }

  /**
   * This is the exit button if clicked the program or the sorter exits but the file will be written already. This method also
   * exits the main or the client method.
   * @param evt
   */
  private void exitProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {
    int close = JOptionPane.showConfirmDialog(this, "Do you really want to close the Sorting Application?", "Exit Confirm", JOptionPane.YES_NO_OPTION );
    if(close == JOptionPane.YES_OPTION) this.dispose();
  }

  /**
   * This is the button that displays the help message. It contains information on how the Application work and how to use.
   * @param evt
   */
  private void helpUsingButtonActionPerformed(java.awt.event.ActionEvent evt) {
    JOptionPane.showMessageDialog(null, "This is an Image Sorting Application that compares the run time of different algorithms in different cases,\n"
                                  + "This Sorting Application uses the Red, Green and Blue mean color components that have been calculated by the\n"
                                  + "Application just after execution. This Application includes a resorting menu where the user can see/compare\n"
                                  + "how different algorithms perfom for cases that the user has provided to the Sorting Application. If the sort \n"
                                  + "button is clicked the images are sorted using the paramets that are determined by the radio group buttons in\n"
                                  + "in the resorting menu and then there after the Application displays the image Slide Show in the image canvas\n"
                                  + "in ascending order of the mean color component specified by the user of the application.", "Help Message",
                                  JOptionPane.INFORMATION_MESSAGE);
  }

/*
 ****************************************************************************************************************************************************************************
 ****************************************************************************************************************************************************************************
 ****************************************************************************************************************************************************************************
 **************************************************** GRAPHICAL USER INTERFACE COMPONENTS INITIALIZER ***********************************************************************
 **************************************************** GRAPHICAL USER INTERFACE COMPONENTS INITIALIZER ***********************************************************************
 **************************************************** GRAPHICAL USER INTERFACE COMPONENTS INITIALIZER ***********************************************************************
 ****************************************************************************************************************************************************************************
 ****************************************************************************************************************************************************************************
 ****************************************************************************************************************************************************************************
 */

 private javax.swing.JLabel TimeDisplayer;
 private javax.swing.JRadioButton blueRadioButton;
 private javax.swing.ButtonGroup buttonGroup1;
 private javax.swing.ButtonGroup buttonGroup2;
 private javax.swing.JButton exitProgramButton;
 private javax.swing.JButton firstButton;
 private javax.swing.JRadioButton greenRadioButton;
 private javax.swing.JButton helpUsingButton;
 private javax.swing.JLabel imageSlideCanvas;
 private javax.swing.JRadioButton insertionSortButton;
 private javax.swing.JButton jButton4;
 private javax.swing.JFrame jFrame1;
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JLabel jLabel3;
 private javax.swing.JLabel jLabel4;
 private javax.swing.JLabel jLabel5;
 private javax.swing.JLabel jLabel9;
 private javax.swing.JPanel jPanel1;
 private javax.swing.JButton lastButton;
 private javax.swing.JRadioButton mergeSortRadioButton;
 private javax.swing.JButton nextButton;
 private java.awt.Panel panel2;
 private javax.swing.JButton previousButton;
 private javax.swing.JRadioButton quickSortRadioButton;
 private javax.swing.JRadioButton redRadioButton;
 private javax.swing.JButton resortImagesButton;
 private javax.swing.JRadioButton selectionSortRadioButton;
 private javax.swing.JRadioButton shellSortRadioButton;

    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        resortImagesButton = new javax.swing.JButton();
        helpUsingButton = new javax.swing.JButton();
        exitProgramButton = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        redRadioButton = new javax.swing.JRadioButton();
        greenRadioButton = new javax.swing.JRadioButton();
        blueRadioButton = new javax.swing.JRadioButton();
        insertionSortButton = new javax.swing.JRadioButton();
        shellSortRadioButton = new javax.swing.JRadioButton();
        mergeSortRadioButton = new javax.swing.JRadioButton();
        quickSortRadioButton = new javax.swing.JRadioButton();
        selectionSortRadioButton = new javax.swing.JRadioButton();
        lastButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        imageSlideCanvas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TimeDisplayer = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(163, 160, 155));
        setPreferredSize(new java.awt.Dimension(1265, 590));
        setResizable(false);

        resortImagesButton.setBackground(new java.awt.Color(109, 105, 105));
        resortImagesButton.setText("Resort Images");
        resortImagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resortImagesButtonActionPerformed(evt);
            }
        });

        helpUsingButton.setBackground(new java.awt.Color(109, 105, 105));
        helpUsingButton.setText("Help Using Program");

        exitProgramButton.setBackground(new java.awt.Color(109, 105, 105));
        exitProgramButton.setText("Exit Program");
        exitProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitProgramButtonActionPerformed(evt);
            }
        });

        panel2.setBackground(new java.awt.Color(133, 118, 118));
        panel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        jButton4.setBackground(new java.awt.Color(109, 105, 105));
        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jButton4.setText("Sort And Display");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton4ActionPerformed(evt);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Menu:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setText("Resorting");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("Color To be Used For Sorting:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("Algorithm To be Used for Sorting: ");

        buttonGroup1.add(redRadioButton);
        redRadioButton.setSelected(true);
        redRadioButton.setText("0 = Red");
        redRadioButton.setEnabled(false);

        buttonGroup1.add(greenRadioButton);
        greenRadioButton.setText("1 = Green");
        greenRadioButton.setEnabled(false);

        buttonGroup1.add(blueRadioButton);
        blueRadioButton.setText("2 = Blue");
        blueRadioButton.setEnabled(false);

        buttonGroup2.add(insertionSortButton);
        insertionSortButton.setSelected(true);
        insertionSortButton.setText("0 = Insertion Sort");
        insertionSortButton.setEnabled(false);

        buttonGroup2.add(shellSortRadioButton);
        shellSortRadioButton.setText("1 = Shell Sort");
        shellSortRadioButton.setEnabled(false);

        buttonGroup2.add(mergeSortRadioButton);
        mergeSortRadioButton.setText("2 = Merge Sort");
        mergeSortRadioButton.setEnabled(false);

        buttonGroup2.add(quickSortRadioButton);
        quickSortRadioButton.setText("3 = Quick Sort");
        quickSortRadioButton.setEnabled(false);

        buttonGroup2.add(selectionSortRadioButton);
        selectionSortRadioButton.setText("4 = Selection Sort");
        selectionSortRadioButton.setEnabled(false);

        helpUsingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpUsingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(insertionSortButton)
                            .addComponent(shellSortRadioButton)
                            .addComponent(mergeSortRadioButton)
                            .addComponent(quickSortRadioButton)
                            .addComponent(selectionSortRadioButton)))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(redRadioButton)
                            .addComponent(greenRadioButton)
                            .addComponent(blueRadioButton)))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton4)))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(81, Short.MAX_VALUE)))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueRadioButton)
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertionSortButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shellSortRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mergeSortRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quickSortRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionSortRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(514, Short.MAX_VALUE)))
        );

        lastButton.setBackground(new java.awt.Color(109, 105, 105));
        lastButton.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lastButton.setText("Last");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        nextButton.setBackground(new java.awt.Color(109, 105, 105));
        nextButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        nextButton.setText(">>>>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        previousButton.setBackground(new java.awt.Color(109, 105, 105));
        previousButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        previousButton.setText("<<<<");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        firstButton.setBackground(new java.awt.Color(109, 105, 105));
        firstButton.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        firstButton.setText("First");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        imageSlideCanvas.setBackground(new java.awt.Color(166, 166, 166));
        imageSlideCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jPanel1.setBackground(new java.awt.Color(166, 166, 166));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel5.setText("Time Taken to Sort is:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel9.setText("Seconds(Secs)");

        TimeDisplayer.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        TimeDisplayer.setForeground(new java.awt.Color(228, 18, 18));
        TimeDisplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TimeDisplayer.setText("                ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TimeDisplayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TimeDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resortImagesButton)
                                .addGap(26, 26, 26)
                                .addComponent(helpUsingButton)
                                .addGap(18, 18, 18)
                                .addComponent(exitProgramButton)
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(firstButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(previousButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageSlideCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageSlideCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastButton)
                                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(resortImagesButton)
                                    .addComponent(helpUsingButton)
                                    .addComponent(exitProgramButton)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
}
