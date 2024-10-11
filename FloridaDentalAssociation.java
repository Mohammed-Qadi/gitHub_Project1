import java.util.Scanner;
//-------------------------------------------------------------------------------------------------

/**
 * This program records what teeth types the members of a Floridian family has
 *
 * @author Mohammed Qadi
 */
public class FloridaDentalAssociation {
//-------------------------------------------------------------------------------------------------
    /**
     * Global Scanner object to use keyboard
     */
    private static final Scanner keyboard = new Scanner(System.in);
    /**
     * INCISORS is 'I'
     */
    private static final char INCISORS = 'I';
    /**
     * BICUSPIDS is 'B'
     */
    private static final char BICUSPIDS = 'B';
    /**
     * MISSING is 'M'
     */
    private static final char MISSING = 'M';
    /**
     * MAXIMUM_FAMILY is 6
     */
    private static final int MAXIMUM_FAMILY = 6;
    /**
     * LAYERS is 2
     */
    private static final int LAYERS = 2;
    /**
     * MAXIMUM_TEETH is 8
     */
    private static final int MAXIMUM_TEETH = 8;
//-------------------------------------------------------------------------------------------------
    /**
     * The main method
     *
     * @param args Passed in from the command line
     */
    public static void main(String[] args) {

//Variables to hold numberOfMembers, String array called names, char array called dataSet
        int numberOfMembers;
        String[] names;
        char[][][] dataSet;
//Welcome the user
//Get the number of family members which is the size of the String array, and the plane size of the char array
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("-----------------------------------------");
        System.out.print("Please enter number of people in the family : ");
        numberOfMembers = keyboard.nextInt();
        while (numberOfMembers <= 0 || numberOfMembers > MAXIMUM_FAMILY) {
            System.out.print("Invalid number of people, try again : ");
            numberOfMembers = keyboard.nextInt();
        }//end of the numberOfMembers idiot-proof
        names = new String[numberOfMembers];
        dataSet = new char[numberOfMembers][LAYERS][];
        dataSet = assigningTeeth(names, dataSet);
        menuOptions(dataSet, names);
    }//end of the main method

    /**
     * Assigns the upper and lower teeth for each family member
     *
     * @param names   the String array of the family members' names
     * @param dataSet the char array which will hold the types of teeth in 2 layers
     * @return the dataSet with the ready assigned teeth to the main method
     */
    private static char[][][] assigningTeeth(String[] names, char[][][] dataSet) {
        int familyMembers, eachTooth, index;
        String nameOfMember, upperTeeth, lowerTeeth;
//------iterate through the names array to assign the name for each family member
        for (familyMembers = 0; familyMembers < names.length; familyMembers++) {
            System.out.print("Please enter the name for family member " + (familyMembers + 1) + " : ");
            nameOfMember = keyboard.next();
            names[familyMembers] = nameOfMember;
            System.out.print("Please enter the uppers for " + names[familyMembers] + " : ");
            upperTeeth = keyboard.next();
            upperTeeth = upperTeeth.toUpperCase();
//----------iterate through the upperTeeth to check for valid characters and valid length
            for (index = 0; index < upperTeeth.length(); index++) {
                while (upperTeeth.charAt(index) != INCISORS && upperTeeth.charAt(index) != MISSING && upperTeeth.charAt(index) != BICUSPIDS) {
                    System.out.print("Invalid teeth Types, try again : ");
                    upperTeeth = keyboard.next();
                    upperTeeth = upperTeeth.toUpperCase();
                    while (upperTeeth.length() > MAXIMUM_TEETH) {
                        System.out.print("Too many teeth, try again : ");
                        upperTeeth = keyboard.next();
                        upperTeeth = upperTeeth.toUpperCase();
                    }
                }
            }
            dataSet[familyMembers][0] = new char[upperTeeth.length()];
//----------iterate through the char array to assign the teeth type for the upper layer
            for (eachTooth = 0; eachTooth < upperTeeth.length(); eachTooth++) {
                dataSet[familyMembers][0][eachTooth] = Character.toUpperCase(upperTeeth.charAt(eachTooth));
            } // end of assigning the teeth to the upper layer
            System.out.print("Please enter the lowers for " + names[familyMembers] + " : ");
            lowerTeeth = keyboard.next();
            lowerTeeth = lowerTeeth.toUpperCase();
//----------iterate through the lowerTeeth to check for valid characters and valid length
            for (index = 0; index < lowerTeeth.length(); index++) {
                while (lowerTeeth.charAt(index) != INCISORS && lowerTeeth.charAt(index) != MISSING && lowerTeeth.charAt(index) != BICUSPIDS) {
                    System.out.print("Invalid teeth Types, try again : ");
                    lowerTeeth = keyboard.next();
                    lowerTeeth = lowerTeeth.toUpperCase();
                    while (lowerTeeth.length() > MAXIMUM_TEETH) {
                        System.out.print("Too many teeth, try again : ");
                        lowerTeeth = keyboard.next();
                        lowerTeeth = lowerTeeth.toUpperCase();
                    }
                }
            }
//----------iterate through the char array to assign the teeth type for the lower layer
            dataSet[familyMembers][1] = new char[lowerTeeth.length()];
            for (eachTooth = 0; eachTooth < lowerTeeth.length(); eachTooth++) {
                dataSet[familyMembers][1][eachTooth] = Character.toUpperCase(lowerTeeth.charAt(eachTooth));
            }// end of assigning the teeth to the lower layer
        }//end of the plane loop
        return dataSet;
    }//end of the assigningTeeth method

    /**
     * A method that is called to display the menu options whether to print, extract, display roots, and exit.
     *
     * @param names   the String array of the family members' names, which will be passed into every method.
     * @param dataSet the char array that holds the type of teeth,  which will be passed into every method.
     */
    private static void menuOptions(char[][][] dataSet, String[] names) {
        //-----A variable to hold the option character
        char option;
        System.out.println();
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it : ");
        option = keyboard.next().charAt(0);
        option = Character.toUpperCase(option);
        //check for a valid menu option
        while (option != 'P' && option != 'E' && option != 'R' && option != 'X') {
            System.out.print("Invalid menue option, try again : ");
            option = keyboard.next().charAt(0);
            option = Character.toUpperCase(option);
        }
//-----A switch statement for the different options, thus calling different methods.
        switch (option) {
            case 'P':
                printData(dataSet, names);
                break;
            case 'E':
                extractTooth(dataSet, names);
                break;
            case 'R':
                rootCanal(dataSet, names);
                break;
            case 'X':
                exitMethod();
                break;

        }

    }//end of the menuOptions method

    /**
     * A method that is called to print the dataSet.
     *
     * @param names   the String array of the family members' names.
     * @param dataSet the char array that holds the type of teeth.
     */
    private static void printData(char[][][] dataSet, String[] names) {
        int familyMembers, eachTooth;
        //----A nested for loop to print out the name, followed by the corresponding upper and lower teeth layer.
        for (familyMembers = 0; familyMembers < names.length; familyMembers++) {
            System.out.println(names[familyMembers]);
            System.out.print("Uppers : ");
            for (eachTooth = 0; eachTooth < dataSet[familyMembers][0].length; eachTooth++) {
                System.out.print(eachTooth + 1 + ":" + dataSet[familyMembers][0][eachTooth] + " ");
            }
            System.out.println();
            System.out.print("Lowers : ");
            for (eachTooth = 0; eachTooth < dataSet[familyMembers][1].length; eachTooth++) {
                System.out.print(eachTooth + 1 + ":" + dataSet[familyMembers][1][eachTooth] + " ");
            }
            System.out.println();
        }
        menuOptions(dataSet, names);

    }//end of the printData method

    /**
     * A method that is called to extract a tooth that is not missing, thus converting it to missing.
     *
     * @param names   the String array of the family members' names to check for a valid name.
     * @param dataSet the char array that holds the type of teeth.
     */
    private static void extractTooth(char[][][] dataSet, String[] names) {
//-----Variables to hold  memberNam, validName, toothNumber, layerOption, index
        String memberName;
        boolean validName = false;
        int toothNumber;
        char layerOption;
        int index;
        System.out.print("Which family member : ");
//------Checking out for a valid input name
        do {
            memberName = keyboard.next();
            for (index = 0; index < names.length; index++) {
                if (memberName.equalsIgnoreCase(names[index])) {
                    validName = true;
                    break;
                }
            }
            if (!validName) {
                System.out.print("Invalid family member, try again: ");
            }
        } while (!validName);
//------For a valid name iterate through the chosen layer of teeth to extract a INCISORS or BICUSPIDS
        System.out.print("Which tooth layer (U)pper or (L)ower : ");
        layerOption = keyboard.next().charAt(0);
        layerOption = Character.toUpperCase(layerOption);
        while (layerOption != 'U' && layerOption != 'L') {
            System.out.print("Invalid layer, try again : ");
            layerOption = keyboard.next().charAt(0);
            layerOption = Character.toUpperCase(layerOption);
        }
        System.out.print("Which tooth number : ");
        toothNumber = keyboard.nextInt();

        if (layerOption == 'U') {
            while (toothNumber - 1 > dataSet[index][0].length) {
                System.out.print("Invalid tooth number, try again : ");
                toothNumber = keyboard.nextInt();
            }
            while (dataSet[index][0][toothNumber - 1] == MISSING) {
                System.out.print("Missing tooth, try again : ");
                toothNumber = keyboard.nextInt();
            }
            if (dataSet[index][0][toothNumber - 1] != MISSING) {
                dataSet[index][0][toothNumber - 1] = MISSING;
            }


        }

        if (layerOption == 'L') {
            while (toothNumber - 1 > dataSet[index][1].length) {
                System.out.print("Invalid tooth number, try again : ");
                toothNumber = keyboard.nextInt();
            }
            while (dataSet[index][1][toothNumber - 1] == MISSING) {
                System.out.print("Missing tooth, try again : ");
                toothNumber = keyboard.nextInt();
            }
            if (dataSet[index][1][toothNumber - 1] != MISSING) {
                dataSet[index][1][toothNumber - 1] = MISSING;
            }


        }


        menuOptions(dataSet, names);

    }//end of the extractData method

    /**
     * A method that is called to report the family's root canal indices.
     *
     * @param names   the String array of the family members' names.
     * @param dataSet the char array that holds the type of teeth.
     */

    private static void rootCanal(char[][][] dataSet, String[] names) {
//------Variables to hold numberOfB, numberOfI, numberOfM, a count for each type of teeth.
        int numberOfB = 0;
        int numberOfI = 0;
        int numberOfM = 0;
//------Variables to hold rootCanal1, rootCanal2, discriminant.
        double rootCanal1;
        double rootCanal2;
        double discriminant;
        int upperIndex, lowerIndex, familyMembers;
//------Iterate through each family member, its upper and lower teeth layer, and increase the count as suitable.
        for (familyMembers = 0; familyMembers < names.length; familyMembers++) {
            for (upperIndex = 0; upperIndex < dataSet[familyMembers][0].length; upperIndex++) {
                if (dataSet[familyMembers][0][upperIndex] == MISSING) {
                    ++numberOfM;
                } else if (dataSet[familyMembers][0][upperIndex] == BICUSPIDS) {
                    ++numberOfB;
                } else if (dataSet[familyMembers][0][upperIndex] == INCISORS) {
                    ++numberOfI;
                }

            }
            for (lowerIndex = 0; lowerIndex < dataSet[familyMembers][1].length; lowerIndex++) {
                if (dataSet[familyMembers][1][lowerIndex] == MISSING) {
                    ++numberOfM;
                } else if (dataSet[familyMembers][1][lowerIndex] == BICUSPIDS) {
                    ++numberOfB;
                } else if (dataSet[familyMembers][1][lowerIndex] == INCISORS) {
                    ++numberOfI;
                }

            }
        }
//------Defining the discriminant as b^2 - 4ac, where b,a,c corresponds to numberOfB. numberOfI, numberOfC respectively
        discriminant = (Math.pow(numberOfB, 2) - (4 * numberOfI * (-numberOfM)));
//------Computing the family root indices based on  Ix2 + Bx - M.
        rootCanal1 = (-numberOfB + Math.sqrt(discriminant)) / (2 * numberOfI);
        rootCanal2 = (-numberOfB - Math.sqrt(discriminant)) / (2 * numberOfI);
//------Displaying the result
        System.out.printf("One root canal at %.2f\n", rootCanal1);
        System.out.printf("Another root canal at %.2f\n", rootCanal2);
        menuOptions(dataSet, names);
    }//end of the rootCanal method

    /**
     * A method that is called to exit the program..
     */
    private static void exitMethod() {
        System.out.print("Exiting the Floridian Tooth Records :-)");
    }//end of the exitMethod

}//end of the FloridaDentalAssociation class
