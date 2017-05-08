# README
*********************************************************************************************************************
*********************************************************************************************************************
### Author		      :Bhekimpilo Ndhlela
### NAME OF PROJECT :IMAGESORTER
*********************************************************************************************************************
*********************************************************************************************************************

## Dependancies	:

Image Soter depends on classes written by me(Bhekimpilo Ndhlela) which include:

       SorterUnit.java

       ProgramGUI.java

       CalculateMeanColor.java

       ImageSort.java (main/client library)

       SorterInitializer.java

       AppTester.java (for coverage testing purposes)


It also depends on the sorting algorithms libraries from Princeton University, I rewrote some of them and edited most of them these algorithms

include:

       Shell.java

       Merge.java

       Insertion.java

       Selection.java

       Quick.java


## How To Run	:
Compilation	: javac ImageSort.java

Ignore The Following notes and execute.

     Note: Some input files use unchecked or unsafe operations.

     Note: Recompile with -Xlint:unchecked for details.


### Menu for Algorithms:

The choice for algorithm to sort is args[1]

      0 = Use Insertion sort to sort

      1 = Use Shell sort to sort

      2 = Use Merge sort to sort

      3 = Use Quick sort to sort

      4 = Use Selection sort to sort

### Menu for Color Choice

The choice for color to sort is args[2]

      0 = use Red  to sort

      1 = use Green to sort

      2 = use Blue to sort


### Choice User Interface

An integer indicating whether the GUI of the program should be launched args[3]

      0 = use the OFF GUI state mode/ dont trigger the GUI

      1 = use the ON GUI state mode/ do trigger the GUI


##  Execution	: java ImageSort "Image Path" algorithmToUse colorToUse GUIstate

      For Example: java ImageSort images 3 1 0

      With these arguments the program must sort the jpg images in the "images" directory based on their mean green value,

      using quicksort and without launching the program's GUI.


## What Happens	:

     This Project, Application, or Program is for sorting a bunch of images using different algorithms which include Insertion,
     Merge, Selection, Quick and Shell sorting algorithms and sorting them using different color parameters e.g. the Red, Green or
     Blue Components of individual images. Sorting is initialised or triggered in two different ways, one way to trigger the
     sorting is to use the Graphical User Interface and the other way is when the Graphical User Interface is OFF the sorting
     happens behind the scenes. In the OFF GUI state, you execute the "Application" and then the sorted.txt is updated, by
     opening the sorted.txt file you will see a list of strings which are basically the individual image name in ascending
     sorted order. If the "Application" is executed more than twice, the sorted.txt will be updated rather than creating a different
     text file.
