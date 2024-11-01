import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a class for our Object Oriented Programming course project.
 */
public class Group21 {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor for the {@code project} class.
     */
    public Group21() {
        // Default constructor - no specific initialization
    }

    /**
     * Starts the main menu.
     * 
     * @param args an array of {@code String} arguments supplied from the command line.
     */
    public static void main(String[ ] args)
    {
        String operation = "";
        clear();
        asciiArt();
        while(!operation.equals("E")){
            System.out.println("[A] Statistical Information about an array");
            System.out.println("[B] Matrix Operations");
            System.out.println("[C] Text Encryption/Decryption");
            System.out.println("[D] Tic-tac-toe HotSeat");
            System.out.println("[E] Terminate.");
            System.out.print("Choose the operation you want to do: ");
            operation = scanner.nextLine();
            operation = inputControl(operation, 1, 0);
            clear();
            switch(operation){
                case "A":
                    arrayMain();
                    break;
                case "B":
                    matrixMain();
                    break;
                case "C":
                    textED();
                    break;
                case "D":
                    game();
                    break;
                case "E":
                    break;
            }
            clear();
        }
        scanner.close();
    } 
 

// GENERAL FUNCTIONS THAT WE WILL USE IN MORE THAN ONE FUNCTION
//*****************************************************************************
//*****************************************************************************


    /**
     * Clears the terminal
     * 
     */
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Checks the input according to the given selection of the operation, and gets another input from user if the previously given input is incorrect
     * 
     * @param input the input given from the user
     * @param selection the selection for different types of inputs.
     * @param dimension amount of numbers that needs to be typed
     * @return the input again
     */
    public static String inputControl(String input, int selection, int dimension){
        int correctInput;
        while(input.length() == 0 && selection != 1){
            System.out.print("Incorrect input, please type again: ");
            input = scanner.nextLine();
        }
        switch(selection){
            case 1:
                char check = '5';
                if(input.length() != 0)
                    check = input.charAt(0);
                while(input.length() != 1 || check < 'A' || check > 'E'){
                    System.out.print("Incorrect input, please type again: ");
                    input = scanner.nextLine();
                    while(input.length() == 0){
                        System.out.print("Incorrect input, please type again: ");
                        input = scanner.nextLine();
                    }
                    check = input.charAt(0);
                }
                break;
            case 2:
                correctInput = 0;
                while(correctInput == 0){
                    correctInput = 1;
                    char current;
                    if(input.contains(",")){
                        int comma = input.indexOf(',');
                        if(comma != 0 || comma != input.length()){
                            String x = input.substring(0,comma);
                            String y = input.substring(comma + 1);
                            for(int i = 0; i < x.length(); i++){
                                current = x.charAt(i);
                                if(current == '0' && i == 0)
                                    correctInput = 0;
                                if(current < '0' || current > '9')
                                    correctInput = 0;
                            }
                            for(int i = 0; i < y.length(); i++){
                                current = y.charAt(i);
                                if(current == '0' && i == 0)
                                    correctInput = 0;
                                if(current < '0' || current > '9')
                                    correctInput = 0;
                            }
                        }
                        else
                            correctInput = 0;
                    }
                    else{
                        correctInput = 0;
                    }
                    if(input.equals("X"))
                        correctInput = 1;
                    if(correctInput == 0){
                        System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                        input = scanner.nextLine();
                    }
                }
                break;
            case 3:
                correctInput = 0;
                while(correctInput == 0){
                    correctInput = 1;
                    int commaCount = 0;
                    int prevComma = -1; 
                    int latestDot = -2;
                    for(int i = 0; i < input.length(); i++){
                        char current = input.charAt(i);
                        if(current < '0' || current > '9'){
                            if(current == '.' && latestDot < prevComma){
                                latestDot = i;
                            }
                            else if(current == ',' && i != prevComma + 1 && i != input.length() - 1){
                                if(latestDot < prevComma){
                                    correctInput = 0;
                                }
                                prevComma = i;
                                commaCount++;
                            }
                            else if(current == '-' && (i == 0 || input.charAt(i - 1) == ','));
                            else{
                                correctInput = 0;
                            }
                        }
                        if((i == input.length() - 1) && latestDot < prevComma)
                            correctInput = 0;
                    }
                    if(dimension != 0 && commaCount != dimension - 1)
                        correctInput = 0;

                    if(input.equals("X"))
                        correctInput = 1;

                    if(correctInput == 0){
                        System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                        input = scanner.nextLine();
                        while(input.length() == 0){
                            System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                            input = scanner.nextLine();
                        }
                    }
                }
                break;
            case 4:
                correctInput = 0;
                while(correctInput == 0){
                    correctInput = 1;
                    for(int i = 0; i < input.length(); i++){
                        if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                            if(i != 0 || input.charAt(i) != '-'){
                                correctInput = 0;
                            }
                        }
                    }
                    if(input.equals("X"))
                        correctInput = 1;
                    
                    if(correctInput == 0){
                        System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                        input = scanner.nextLine();
                        while(input.length() == 0){
                            System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                            input = scanner.nextLine();
                        }
                    }
                }
        }
        return input;
    }

    /**
     * Converts a given string that contains only one number in double format to a double.
     * 
     * @param input the part of a string that needs to be converted to a double
     * @return the input as a double
     */
    public static double toDouble(String input){
        boolean isNegative = false;
        double value = 0;
        int dot = input.indexOf('.');
        for(int i = 0 ; i < input.length(); i++){
            if(input.charAt(i) == '-')
                isNegative = true;
            else{
                if(input.charAt(i) != '.'){
                    double number = input.charAt(i) - '0';
                    value += digit(number, dot - i - 1);
                }
            }
        }
        if(isNegative)
            value *= -1;
        return value;
    }

    /**
     * Converts a given string that contains only one number in integer format to an integer.
     * 
     * @param input the part of a string that needs to be converted to an integer
     * @return the input as an integer
     */
    public static int toInt(String input){
        boolean isNegative = false;
        int value = 0;
        for(int i = 0 ; i < input.length(); i++){
            if(input.charAt(i) == '-')
                isNegative = true;
            else{
                int number = input.charAt(i) - '0';
                value += digit(number, input.length() - i - 1);
            }
        }
        if(isNegative)
            value *= -1;
        return value;
    }

    /**
     * This method shifts a double value’s decimal point by the specified digit value. A positive digit moves it right, a negative digit moves it left. For example, digit(5, 4) returns 50000. 
     * 
     * @param value given number
     * @param digit digit value to shift the digit
     * @return shifted version of the number
     */
    public static double digit(double value, int digit){
        if(digit > 0)
            for(int i = 0; i < digit; i++){
            value *= 10;
            }
        if(digit < 0){
            digit++;
            for(; digit < 0; digit++){
                value /= 10;
            }
        }
        return value;
    }

    /**
     * This method appends zeros to value by multiplying it by 10 repeatedly, based on digit. For example, digit(5, 3) returns 5000.
     * 
     * @param value given number
     * @param digit digit value to shift the digit
     * @return shifted version of the number
     */
    public static int digit(int value, int digit){
        for(int i = 0; i < digit; i++){
        value *= 10;
        }
        return value;
    }

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// Array Functions
//*****************************************************************************
//*****************************************************************************


    /**
     * This is the method for the main menu of array functions which also converts the given input into an array of doubles.
     */
    public static void arrayMain(){
        String input;
        int negativeCount = 0; int zeroCount = 0;
        System.out.print("Please type the values for your array in double format using dots (eg. 2.3,3.4) with commas in between without any space(eg. 2,3,4) type 'X' to go back to previous menu: ");
        input = scanner.nextLine();
        input = inputControl(input, 3, 0);
        
        if(!input.equals("X")){
            int commaCount = 0;
            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) == ',')
                    commaCount++;
            }
            double[] arr = new double[commaCount + 1];

            double value;
            int comma1;
            int comma2 = -1;
            for(int i = 0; i < commaCount + 1; i++){
                value = 0.0;
                if(i == commaCount){
                    value = toDouble(input.substring(comma2 + 1));
                }
                else{
                    comma1 = comma2 + 1;
                    comma2 = input.indexOf(',', comma1);
                    value = toDouble(input.substring(comma1, comma2));
                }
                if(value < 0)
                    negativeCount++;
                if(value == 0)
                    zeroCount++;
                arr[i] = value;
            }
            clear();
            System.out.print("Your array: ");
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i]);
                if(i != arr.length - 1)
                    System.out.print(",");
            }
            System.out.println("\n");
            System.out.println("Median: " + med(arr));
            System.out.println("Mean: " + mean(arr));
            if(negativeCount == 0)
                System.out.println("Geometric mean: " + geo_mean(arr));
            else
                System.out.println("Geometric mean could not be calculated since the array contains negative numbers");
            if(zeroCount == 0)
                System.out.println("Harmonic mean: " + harmonic(arr,0, arr.length - 1));
            else
                System.out.println("Harmonic mean could not be calculated since the array contains zero");
            System.out.print("\nPress enter to continue: ");
            scanner.nextLine();
        }
    }


    /**
     * A method that sorts the given array and calculates the median of the array
     * 
     * @param arr given array
     * @return median of the array
     */
    public static double med(double[] arr){

        Arrays.sort(arr);

        if(arr.length % 2 != 0){
            return arr[arr.length/2];
        }else {
            return (arr[arr.length/2] + arr[(arr.length/2) - 1]) / 2;
        }
    }

    /**
     * A method that calculates the mean of the given array.
     * 
     * @param arr given array
     * @return mean of the array
     */
    public static double mean(double[] arr){
        double sum = 0;

        for(int i = 0; i < arr.length;i++){
            sum +=arr[i];
        }
        return sum / (double)arr.length;
    }

    /**
     * A method that that calculates the geometric mean of the given array.
     * 
     * @param arr given array
     * @return geometric mean of the array
     */
    public static double geo_mean(double[] arr){
        double result = 1;

        for(int i = 0; i < arr.length; i++){
            result *= arr[i];
        }
        return Math.pow(result, 1.0 / arr.length);

    }

    /**
     * A method that calculates the harmonic mean of the given array.
     * 
     * @param arr given array
     * @param left  left border for the recursive call, use 0 for the first call
     * @param right right border for the recursive call, use array's size - 1 for the first call
     * @return harmonic mean of the array
     */
    public static double harmonic(double[] arr, int left, int right){
        if(left == right){
            return 1 / arr[right];
        }
        int middle = (right + left) / 2;
        double left_val = harmonic(arr, left, middle);
        double right_val = harmonic(arr, middle + 1, right);

        if(left == 0 && right == arr.length - 1)
            return (arr.length / (left_val + right_val));
        return (left_val + right_val);
    }



//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// Matrix Functions
//*****************************************************************************
//*****************************************************************************

    /**
     * A method for the main menu of matrix operations.
     */
    public static void matrixMain(){
        String input;
        String operation = "";
        while(!operation.equals("E")){
            clear();
            System.out.println("[A] Transpose");
            System.out.println("[B] Inverse");
            System.out.println("[C] Matrix Multiplication");
            System.out.println("[D] Element-Wise Multiplication");
            System.out.println("[E] Return to the Main Menu");
            System.out.print("Choose the operation you want to do: ");
            input = scanner.nextLine();
            operation = inputControl(input, 1, 0);
            clear();
            switch(operation){
                case "A":
                    transposeMain();
                    break;
                case "B":
                    inverse();
                    break;
                case "C":
                    multiply();
                    break;
                case "D":
                    elementWise();
                    break;
                case "E":
                    break;
            }
        }
    }
    /**
     * A method that takes a matrix as an input from the user to calculate the transpose of that matrix.
     */
    public static void transposeMain(){
        clear();
        int[] dimensions = new int[2];
        String input = getDimension(dimensions, "");
        if(!input.equals("X")){
            int x = dimensions[0];
            int y = dimensions[1];
            double[][] matrix1 = new double[x][y];
            input = getValue(matrix1, x, y, " ");
            if(!input.equals("X")){
                double[][] matrix2 = new double[y][x];
                transpose(matrix1, matrix2, x, y);
                clear();
                System.out.println("Your matrix: ");
                printMatrix(matrix1, x, y);
                System.out.println("Transpose of your matrix: ");
                printMatrix(matrix2, y, x);
                System.out.print("Press enter to continue: ");
                scanner.nextLine();
            }
        }
    }

    /**
     * A method that saves the transpose of matrix1 to matrix2.
     * 
     * @param matrix1 given matrix
     * @param matrix2 an empty matrix to save the transpose matrix
     * @param x the total number of rows in matrix1
     * @param y the total number of columns in matrix1
     */
    public static void transpose(double[][] matrix1, double[][] matrix2, int x, int y){
        for(int i = 0; i < x * y; i++){
            matrix2[i % y][i / y] = matrix1[i / y][i % y];
        }
    }


    
    /**
     * A method that takes a matrix as an input from the user and calculates the inverse of that matrix.
     */
    public static void inverse(){
        clear();
        int[] dimensions = new int[2];
        String input = getDimension(dimensions, "");
        while((dimensions[0] != dimensions[1] || dimensions[0] == 1) && !input.equals("X")){
            System.out.println("This matrix is not invertible, both dimensions needs to be the same. Please try again.");
            input = getDimension(dimensions, "");
        }
        if(!input.equals("X")){
            int x = dimensions[0];
            int y = dimensions[1];
            double[][] matrix = new double[x][y];
            input = getValue(matrix, x, y, " ");
            if(!input.equals("X")){
                double determinant = determinant(matrix, x, y);
                if(determinant != 0){
                    double[][] cofactor = new double[x][y];
                    cofactor(matrix, cofactor, x, y);
                    double[][] transpose = new double[x][y];
                    transpose(cofactor, transpose, x, y);
                    for(int i = 0; i < x; i++){
                        for(int j = 0; j < y; j++){
                            transpose[i][j] /= determinant;
                        }
                    }
                    System.out.println("Your matrix: ");
                    printMatrix(matrix, x, y);
                    System.out.println("Inverse of your matrix: ");
                    printMatrix(transpose, x, y);
                    System.out.print("Press enter to continue: ");
                    scanner.nextLine();
                }
                else{
                    System.out.println("This matrix is non-invertible.");
                    System.out.print("Press enter to continue: ");
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * A method that saves the cofactor matrix of matrix1 to matrix2.
     * 
     * @param matrix1 given matrix
     * @param matrix2 an empty matrix to save the cofator matrix
     * @param x the total number of rows in matrix1
     * @param y the total number of columns in matrix1
     */
    public static void cofactor(double[][] matrix1, double[][] matrix2, int x, int y){
        if(x == 2){
            matrix2[0][0] = matrix1[1][1];
            matrix2[0][1] = matrix1[1][0] * -1;
            matrix2[1][0] = matrix1[0][1] * -1;
            matrix2[1][1] = matrix1[0][0];
        }
        else
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    double[][] minor = new double[x-1][y-1];
                    minor(matrix1, minor, i, j, x, y);
                    if((i + j) % 2 == 0)
                        matrix2[i][j] = determinant(minor, x - 1, y - 1);
                    else
                        matrix2[i][j] = determinant(minor, x - 1, y - 1) * -1;
                }
            }
    }

    /**
     * A method that calculates the determinant of the given matrix.
     * 
     * @param matrix given matrix
     * @param x the total number of rows in matrix1
     * @param y the total number of columns in matrix1
     * @return determinant of the given matrix
     */
    public static double determinant(double[][] matrix, int x, int y){
        if(x == 2 && y == 2){
            return((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
        }
        else{
            double[][] minor = new double[x-1][y-1];
            double[] det = new double[y];
            double determinant = 0;
            for(int i = 0; i < y; i++){
                minor(matrix, minor, 0, i, x, y);
                if(i % 2 == 0)
                    det[i] = matrix[0][i] * determinant(minor, x-1, y-1);
                else 
                    det[i] = matrix[0][i] * determinant(minor, x-1, y-1) * -1;
                determinant += det[i];
            }
            return determinant;
        }
    }
    

    /**
     * A method that saves the minor matrix of matrix1 into matrix2, excluding the specified row and column from matrix1.
     * 
     * @param matrix1 the given matrix
     * @param matrix2 an empty matrix to save the minor matrix
     * @param x1 the row index of the element to exclude from matrix1
     * @param y1 the column index of the element to exclude from matrix1
     * @param x the total number of rows in matrix1
     * @param y the total number of columns in matrix1
     */
    public static void minor(double[][] matrix1, double[][] matrix2, int x1, int y1, int x, int y){
        int i1 = 0;
        for(int i = 0; i < x; i++){
            int j1 = 0;
            for(int j = 0; j < y; j++){
                if(i != x1 && j != y1){
                    matrix2[i1][j1] = matrix1[i][j];
                    j1++;
                }
            }
            if(i != x1)
                i1++;
        }
    }

    /**
     * A method that takes two matrixes as inputs from the user and calculates the multiplications of those matrixes.
     */
    public static void multiply(){
        clear();
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        int[] dimensions = new int[2];
        String input;
        while(true){
            input = getDimension(dimensions, "first ");
            if(input.equals("X"))
                break;
            x1 = dimensions[0];
            y1 = dimensions[1];
            input = getDimension(dimensions, "second ");
            if(input.equals("X"))
                break;
            x2 = dimensions[0];
            y2 = dimensions[1];
            clear();
            if(y1 == x2 || y2 == x1)
                break;
            else{
                System.out.println("Incorrect input! Number of rows of the second matrix has to be equal to the number of columns of the first matrix!");
            }
        }
        if(!input.equals("X")){
            double[][] matrix1 = new double[x1][y1];
            double[][] matrix2 = new double[x2][y2];
            getValue(matrix1, x1, y1, "first ");
            if(!input.equals("X"))
                input = getValue(matrix2, x2, y2, "second ");
            if(!input.equals("X")){
                double[][] matrix3 = {};
                if(y1 == x2){
                    matrix3 = new double[x1][y2];
                    for(int i = 0; i < x1; i++){
                        for(int j = 0; j < y2; j++){
                            matrix3[i][j] = 0;
                            for(int k = 0; k < x2; k++){
                                matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
                            }
                        }
                    }
                }
                else if(y2 == x1){
                    matrix3 = new double[x2][y1];
                    for(int i = 0; i < x2; i++){
                        for(int j = 0; j < y1; j++){
                            matrix3[i][j] = 0;
                            for(int k = 0; k < x1; k++){
                                matrix3[i][j] += matrix1[k][j] * matrix2[i][k];
                            }
                        }
                    }
                }
                clear();
                System.out.println("First given matrix: ");
                printMatrix(matrix1, x1, y1);
                System.out.println("Second given matrix: ");
                printMatrix(matrix2, x2, y2);
                System.out.println("Multiplication of given matrixes: ");
                if(y1 == x2)
                    printMatrix(matrix3, x1, y2);
                else if(y2 == x1)
                    printMatrix(matrix3, x2, y1);
                System.out.print("Press enter to continue: ");
                scanner.nextLine();
            }
        }
    }

    /**
     * A method that takes two matrixes as inputs from the user and calculates the element-wise multiplications of those matrixes.
     */
     public static void elementWise(){
        clear();
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        int[] dimensions = new int[2];
        String input;
        while(true){
            input = getDimension(dimensions, "first ");
            if(input.equals("X"))
                break;
            x1 = dimensions[0];
            y1 = dimensions[1];
            input = getDimension(dimensions, "second ");
            if(input.equals("X"))
                break;
            x2 = dimensions[0];
            y2 = dimensions[1];
            if((x1 == x2 && y1 == y2) || (x1 == x2 && (y2 == 1 || y1 == 1)) || ((x2 == 1 || x1 == 1) && y1 == y2) || (x2 == 1 && y2 == 1) || (x1 == 1 && y1 == 1))
                break;
            else{
                System.out.println("Incorrect input! Dimensions of the matrixes needs to be same or one of the matrixes needs to be a column vector or a row vector!");
            }
        }
        if(!input.equals("X")){
            double[][] matrix1 = new double[x1][y1];
            double[][] matrix2 = new double[x2][y2];
            input = getValue(matrix1, x1, y1, "first ");
            if(!input.equals("X"))
                input = getValue(matrix2, x2, y2, "second ");
            if(!input.equals("X")){
                double[][] matrix3 = {};
                int x,y;
                if((x1 == x2 && y1 == y2) || (x1 == x2 && y2 == 1) || (x2 == 1 && y1 == y2) || (x2 == 1 && y2 == 1)){
                    matrix3 = new double[x1][y1];
                    x = x1;
                    y = y1;
                    for(int i = 0; i < x1; i++){
                        for(int j = 0; j < y1; j++){
                            if(x1 == x2 && y1 == y2)
                                matrix3[i][j] = matrix1[i][j] * matrix2[i][j];
                            else if((x1 == x2 && y2 == 1))
                                matrix3[i][j] = matrix1[i][j] * matrix2[i][0];
                            else if(x2 == 1 && y1 == y2)
                                matrix3[i][j] = matrix1[i][j] * matrix2[0][j];
                            else
                                matrix3[i][j] = matrix1[i][j] * matrix2[0][0];
                        }
                    }
                }
                else{
                    x = x2;
                    y = y2;
                    matrix3 = new double[x2][y2];
                    for(int i = 0; i < x2; i++){
                        for(int j = 0; j < y2; j++){
                            if((x1 == x2 && y1 == 1))
                                matrix3[i][j] = matrix1[i][0] * matrix2[i][j];
                            else if(x1 == 1 && y1 == y2)
                                matrix3[i][j] = matrix1[0][j] * matrix2[i][j];
                            else
                                matrix3[i][j] = matrix1[0][0] * matrix2[i][j];
                        }
                    }
                }
                clear();
                System.out.println("First given matrix: ");
                printMatrix(matrix1, x1, y1);
                System.out.println("Second given matrix: ");
                printMatrix(matrix2, x2, y2);
                System.out.println("Multiplication of given matrixes: ");
                printMatrix(matrix3, x, y);
                System.out.print("Press enter to continue: ");
                scanner.nextLine();
            }
        }
    }


    /**
     * A method that returns the number of digits in the given double value.
     * 
     * @param number the given number
     * @return the number of digits in the given double value
     */
    public static int returnDigit(double number){
        double number2 = number;
        if(number < 0)
            number2 *= -1;
        int digit = 0;
        while((int)number2 > 0){
            number2 /= 10;
            digit++;
        }
        if(number == 0.0)
            return 1;
        else if(number < 0)
            return (digit + 1);
        else
            return digit;
    }

    /**
     * A method that prints the given matrix.
     * 
     * @param matrix the matrix that needs to be printed
     * @param x the total number of rows in matrix
     * @param y the total number of columns in matrix
     */
    public static void printMatrix(double[][] matrix, int x, int y){
        double max = 0.0, min = 0.0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(matrix[i][j] < min)
                    min = matrix[i][j];
                if(matrix[i][j] > max)
                    max = matrix[i][j];
            }
        }
        int digitMax = returnDigit(max) + 3;
        int digitMin = returnDigit(min) + 3;
        int digit;

        if(digitMax > digitMin)
            digit = digitMax;
        else
            digit = digitMin;
        for(int i = 0; i < x; i++){
            System.out.print("|");
            for(int j = 0; j < y; j++){
                String formatted = String.format("%.2f", matrix[i][j]);
                formatted = formatted.replace(',', '.');
                if(formatted.equals("-0.00")){
                    String temp = "0.00";
                    formatted = temp;
                }
                int padding1 = (digit - formatted.length()) / 2; // Calculate left padding
                int padding2 = digit - formatted.length() - padding1; // Calculate right padding
                for(int k = 0; k < padding1; k++){
                    System.out.print(" ");
                }
                System.out.print(formatted);
                for(int k = 0; k <padding2; k++){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }    


    /**
     * A method that saves the given values to a matrix.
     * 
     * @param input input of values that needs to be saved within the matrix
     * @param matrix an empty matrix to be filled
     * @param x the total number of rows in matrix
     * @param y the total number of columns in matrix
     */
    public static void setMatrix(String input, double[][] matrix, int x, int y){
        double value;
        int comma1;
        int comma2 = -1;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                value = 0;
                if(i == x - 1 && j == y - 1){
                    value = toDouble(input.substring(comma2 + 1));
                }
                else{
                    comma1 = comma2 + 1;
                    comma2 = input.indexOf(',', comma1);
                    value = toDouble(input.substring(comma1, comma2));
                }
                matrix[i][j] = value;
            }
        }
    }

    /**
     * A method that takes the dimensions of a matrix as an input from the user and returns the input to check if the user type 'X' to go back to previous menu.
     * 
     * @param dimensions a 2 sized array to save the dimensions of a matrix
     * @param matrixNum a string to use in the printed message for the user. Input "first" for the first matrix, input an empty string if you will get only 1 matrix from the user
     * @return the input to check if the user type 'X' to go back to previous menu.
     */
    public static String getDimension(int[] dimensions, String matrixNum){
        String input;
        System.out.print("Please type the dimensions of your " + matrixNum + "matrix with a comma in between without any space(eg. 2,3) type 'X' to go back to previous menu: ");
        input = scanner.nextLine();
        input = inputControl(input, 2, 0);
        clear();
        if(!input.equals("X"))
            returnDimension(input, dimensions);
        return input;
    }

    /**
     * A method that saves the dimensions of a matrix into a 2 sized array.
     * 
     * @param input given input
     * @param dimensions a 2 sized array to save the dimensions of a matrix
     */
    public static void returnDimension(String input, int[] dimensions){
        int comma = input.indexOf(',');
        int value = 0;
        value = toInt(input.substring(0,comma));
        dimensions[0] = value;
        value = toInt(input.substring(comma + 1));
        dimensions[1] = value;
    }

    /**
     * A method that takes the values of a matrix as an input from the user and returns the input to check if the user type 'X' to go back to previous menu.
     * 
     * @param matrix an x*y sized empty matrix to fill
     * @param x the total number of rows in matrix
     * @param y the total number of columns in matrix
     * @param valueNum a string to use in the printed message for the user. Input "First" for the first matrix
     * @return the input to check if the user type 'X' to go back to previous menu.
     */
    public static String getValue(double[][] matrix, int x, int y, String valueNum){
        System.out.print("Please type " + x*y + " values for your " + valueNum + "matrix in double format using dots (eg. 2.3) with commas in between without any space(eg. 2.3,3.4,4.5) type 'X' to go back to previous menu: ");
        String input = scanner.nextLine();
        input = inputControl(input, 3, x*y);
        clear();
        if(!input.equals("X"))
            setMatrix(input, matrix, x, y);
        return input;
    }
    
    

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// Text Encryption/Decryption Functions
//*****************************************************************************
//*****************************************************************************
     /**
     * A method for the main menu of text encyrption/decryption operations.
     */
    public static void textED(){
        String input = "";
        while(!input.equals("C")){
            clear();
            System.out.println("[A] Text Encryption");
            System.out.println("[B] Text Decryption");
            System.out.println("[C] Return to the Previous Menu");
            System.out.print("Choose the operation you want to do: ");
            input = scanner.nextLine();
            char check = '1';
            if(input.length() != 0)
                check = input.charAt(0);
            while(input.length() != 1 || check < 'A' || check > 'C'){
                System.out.print("Incorrect input, please type again: ");
                input = scanner.nextLine();
                if(input.length() != 0)
                    check = input.charAt(0);
            }
            clear();
            switch(input){
                case "A":
                    encrypt();
                    break;
                case "B":
                    decrypt();
                    break;
                case "C":
                    break;
            }
        }
    }

/**
     * A method that takes the text to be encrypted and the encryption key from the user as an input, encrypts the text, and prints the text to the user.
     * 
     */
    public static void encrypt(){
        System.out.print("Please type the text to encrypt: ");
        String text = scanner.nextLine();
        String key;
        int int_key;
        System.out.print("Please type the encryption key between -26 and 26, type 'X' to go back to previous menu: ");
        key = scanner.nextLine();
        key = inputControl(key, 4, 0);
        int_key = toInt(key);
        while(!key.equals("X") && (int_key < -26 || int_key > 26)){ 
            System.out.print("Encryption key needs to be between -26 and 26. Please type again or type 'X' to go back to previous menu: ");
            key = scanner.nextLine();
            key = inputControl(key, 4, 0);
            int_key = toInt(key);
        }
        if(!key.equals("X")){
            String encrypted = "";
            for(int i = 0; i < text.length(); i++){
                char current = text.charAt(i);
                if(int_key < 0){
                    if(current >= 'A' && current <= 'Z'){
                        if(current + int_key < 'A'){
                            encrypted += (char)('Z' - ('A' - (current + int_key) - 1));
                        }
                        else{
                            encrypted += (char)(current + int_key);
                        }
                    }
                    else if(current >= 'a' && current <= 'z'){
                        if(current + int_key < 'a'){
                            encrypted += (char)('z' - (('a' - (current + int_key)) - 1));
                        }
                        else{
                            encrypted += (char)(current + int_key);
                        }
                    }
                    else
                        encrypted += (char)current;
                }
                else{
                    if(current >= 'A' && current <= 'Z'){
                        if(current + int_key > 'Z'){
                            encrypted += (char)('A' + ((current + int_key) - 'Z' - 1));
                        }
                        else{
                            encrypted += (char)(current + int_key);
                        }
                    }
                    else if(current >= 'a' && current <= 'z'){
                        if(current + int_key > 'z'){
                            encrypted += (char)('a' + ((current + int_key) - 'z' - 1));
                        }
                        else{
                            encrypted += (char)(current + int_key);
                        }
                    }
                    else
                        encrypted += (char)current;
                }
            }
            System.out.print("Encrypted message: ");
            System.out.print(encrypted + "\n");
            System.out.print("Press enter to continue: ");
            scanner.nextLine();
        }
    }

    /**
     * A method that takes the text to be decrypted and the decryption key from the user as an input, decrypts the text, and prints the text to the user.
     * 
     */
    public static void decrypt(){
        System.out.print("Please type the text to decrypt: ");
        String text = scanner.nextLine();
        String key;
        int int_key;
        System.out.print("Please type the decryption key between -26 and 26, type 'X' to go back to previous menu: ");
        key = scanner.nextLine();
        key = inputControl(key, 4, 0);
        int_key = toInt(key);
        while(!key.equals("X") && (int_key < -26 || int_key > 26)){ 
            System.out.print("Decryption key needs to be between -26 and 26. Please type again or type 'X' to go back to previous menu: ");
            key = scanner.nextLine();
            key = inputControl(key, 4, 0);
            int_key = toInt(key);
        }
        if(!key.equals("X")){
            String decrypted = "";
            for(int i = 0; i < text.length(); i++){
                char current = text.charAt(i);
                if(int_key > 0){
                    if(current >= 'A' && current <= 'Z'){
                        if(current - int_key < 'A'){
                            decrypted += (char)('Z' - ('A' - (current - int_key) - 1));
                        }
                        else{
                            decrypted += (char)(current - int_key);
                        }
                    }
                    else if(current >= 'a' && current <= 'z'){
                        if(current - int_key < 'a'){
                            decrypted += (char)('z' - (('a' - (current - int_key)) - 1));
                        }
                        else{
                            decrypted += (char)(current - int_key);
                        }
                    }
                    else
                        decrypted += (char)current;
                }
                else{
                    if(current >= 'A' && current <= 'Z'){
                        if(current - int_key > 'Z'){
                            decrypted += (char)('A' + ((current - int_key) - 'Z' - 1));
                        }
                        else{
                            decrypted += (char)(current - int_key);
                        }
                    }
                    else if(current >= 'a' && current <= 'z'){
                        if(current - int_key > 'z'){
                            decrypted += (char)('a' + ((current - int_key) - 'z' - 1));
                        }
                        else{
                            decrypted += (char)(current - int_key);
                        }
                    }
                    else
                        decrypted += (char)current;
                }
            }
            System.out.print("Encrypted message: ");
            System.out.print(decrypted + "\n");
            System.out.print("Press enter to continue: ");
            scanner.nextLine();
        }
    }


//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// TicTacToe Functions
//*****************************************************************************
//*****************************************************************************
    /**
     * A method for the main menu of the Tic Tac Toe game.
     */
    public static void game() {
        int[][] arr = new int[3][3]; // game board
        int y;
        int player = 0;
        int difficulty = 0;
        String input;
        Random rand = new Random(); // random number generator for AI

        pRules();

        // Loop until player chooses 1 (singleplayer) or 2 (multiplayer)
        int moveNumber = 0;
        while (player != 1 && player != 2) {
            System.out.print("\nPress 1 for singleplayer, Press 2 for multiplayer: ");
            input = scanner.nextLine();
            player = inputCheck(input, 1, 2);

            switch (player) {
                case 1: // Singleplayer
                    while (difficulty != 1 && difficulty != 2) {
                        System.out.print("\n|DIFFICULTY|\n1) Easy\n2) Hard\nChoose a difficulty to start: ");
                        input = scanner.nextLine();
                        difficulty = inputCheck(input, 1, 2);

                        switch (difficulty) {
                            case 1: // Easy AI
                                while (true) {
                                    clear();
                                    System.out.println("\nPlayer's turn");
                                    gBoard(arr);
                                    System.out.print("Choose a cell to play: ");
                                    input = scanner.nextLine();
                                    y = inputCheck(input, 1, 9);
                                    y = moveCheck(arr, y);
                                    gMove(arr, 1, y);
                                    moveNumber++;
                                    if (winningCondition(arr, 1, 0) == 1)
                                        break;
                                    if (gOver(arr) == 1)
                                        break;
                                    // AI move
                                    System.out.println("\nComputer's turn");
                                    y = rand.nextInt(9) + 1;
                                    y = moveCheckAi(arr, y);
                                    System.out.println("Computer chose cell " + y + ".");
                                    gMove(arr, 2, y);
                                    moveNumber++;
                                    if (winningCondition(arr, 2, 1) == 1)
                                        break;
                                    if (gOver(arr) == 1)
                                        break;
                                }
                                break;

                            case 2: // Hard AI
                                while (true) {
                                    clear();
                                    System.out.println("\nComputer's turn");
                                    y = hardAi(arr);
                                    if (y == 0) 
                                        y = rand.nextInt(9) + 1;
                                    y = moveCheckAi(arr, y);
                                    System.out.println("Computer chose cell " + y + ".");
                                    gMove(arr, 2, y);
                                    moveNumber++;
                                    if (winningCondition(arr, 2, 1) == 1)
                                        break;
                                    if (gOver(arr) == 1)
                                        break;

                                    System.out.println("\nPlayer's turn");
                                    gBoard(arr);
                                    System.out.print("Choose a cell to play: ");
                                    input = scanner.nextLine();
                                    y = inputCheck(input, 1, 9);
                                    y = moveCheck(arr, y);
                                    gMove(arr, 1, y);
                                    moveNumber++;
                                    if (winningCondition(arr, 1, 0) == 1) 
                                        break;
                                    if (gOver(arr) == 1) 
                                        break;
                                }
                                break;

                            default:
                                System.out.println("\nIncorrect input, try again.");
                        }
                    }
                    break;

                case 2: // Multiplayer
                    while (true) {
                        clear();
                        System.out.println("\nPlayer 1's turn");
                        gBoard(arr);
                        System.out.print("Choose a cell to play: ");
                        input = scanner.nextLine();
                        y = inputCheck(input, 1, 9);
                        y = moveCheck(arr, y);
                        gMove(arr, 1, y);
                        moveNumber++;
                        if (winningCondition(arr, 1, 0) == 1)
                            break;
                        if (gOver(arr) == 1)
                            break;
                        clear();
                        System.out.println("\nPlayer 2's turn");
                        gBoard(arr);
                        System.out.print("Choose a cell to play: ");
                        input = scanner.nextLine();
                        y = inputCheck(input, 1, 9);
                        y = moveCheck(arr, y);
                        gMove(arr, 2, y);
                        moveNumber++;
                        if (winningCondition(arr, 2, 0) == 1)
                            break;
                        if (gOver(arr) == 1)
                            break;
                    }
                    break;

                default:
                    System.out.println("\nIncorrect input, try again.");
            }
        }

        System.out.println("\nGame is finished in " + moveNumber + " moves.");
        gBoard(arr);
        System.out.print("Press enter to continue: ");
        scanner.nextLine();
    }

    /**
     * A method that prints the rules of the game.
     */
    public static void pRules() {
        System.out.println("======================== RULES ====================================");
        System.out.println("1) Players must connect 3 of the same sign in a row to win.");
        System.out.println("2) Only one sign is played at a time.");
        System.out.println("3) Players can be on the offensive or defensive.");
        System.out.println("4) The game ends when there is a 3-in-a-row or a stalemate.");
        System.out.println("-------------------------------------------------------------------");
    }

    
    /**
     * A method that prints the current status of the gameboard.
     * 
     * @param arr gameboard as a matrix
     */
    public static void gBoard(int[][] arr) {
        System.out.println("\n -------------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(" |");
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("   |" + ((3 * i) + j + 1) + "|");
                } 
                else if (arr[i][j] == 1) {
                    System.out.print(" X |" + ((3 * i) + j + 1) + "|");
                } 
                else {
                    System.out.print(" O |" + ((3 * i) + j + 1) + "|");
                }
            }
            System.out.println("\n -------------------");
        }
    }


    /**
     * A method that places the player's move into the gameboard.
     * 
     * @param arr gameboard as a matrix
     * @param pl player number
     * @param y cell number
     */
    public static void gMove(int[][] arr, int pl, int y) {
        arr[(y - 1) / 3][(y - 1) % 3] = pl;
    }

    /**
     * A method that checks the cell that the player chose. Asks for another input if the chosen cell is full. Returns the chosen cell.
     * 
     * @param arr gameboard as a matrix
     * @param y cell number
     * @return chosen cell
     */
    public static int moveCheck(int[][] arr, int y) {
        String input;
        while (arr[(y - 1) / 3][(y - 1) % 3] != 0 || y < 1 || y > 9) {
            if (arr[(y - 1) / 3][(y - 1) % 3] != 0 && !(y < 1 || y > 9)) {
                System.out.print("This cell is full! Choose another cell: ");
            } 
            else {
                System.out.print("You have to choose a number between 1 and 9: ");
            }
            input = scanner.nextLine();
            y = inputCheck(input, 1, 9);
        }
        return y;
    }

    /**
     * A method that checks the cell that the ai chose. Generates another random number if the chosen cell is full. Returns the chosen cell.
     * 
     * @param arr gameboard as a matrix
     * @param y cell number
     * @return chosen cell
     */
    public static int moveCheckAi(int[][] arr, int y) {
        Random rand = new Random();
        while (arr[(y - 1) / 3][(y - 1) % 3] > 0) {
            y = rand.nextInt(9) + 1;
        }
        return y;
    }

    /**
     * A method that checks whether the board is full or not.
     * 
     * @param arr gameboard as a matrix
     * @return 1 if the board is full, 0 if the board is not full
     */
    public static int gOver(int[][] arr) {
        int emptyCellCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) emptyCellCount++;
            }
        }
        if (emptyCellCount == 0) {
            System.out.println("\nNo empty spaces left!");
            System.out.println("\nDraw");
            return 1;
        }
        return 0;
    }


    /**
     * A method that checks whether the current player has won the game or not.
     * 
     * @param arr gameboard as a matrix
     * @param p player number
     * @param comp input 1 if the current player is the computer
     * @return 1 if the player has won the game, 0 if not
     */
    public static int winningCondition(int[][] arr, int p, int comp) {
        for (int x = 0; x < 3; x++) {
            // Check horizontal
            if (arr[x][0] == p && arr[x][1] == p && arr[x][2] == p) {
                if(comp == 1)
                    System.out.println("\nComputer won!");
                else
                    System.out.println("\nPlayer " + p + " won!");
                return 1;
            }
            // Check vertical
            if (arr[0][x] == p && arr[1][x] == p && arr[2][x] == p) {
                if(comp == 1)
                    System.out.println("\nComputer won!");
                else
                    System.out.println("\nPlayer " + p + " won!");
                return 1;
            }
        }
        // Check diagonals
        if (arr[0][0] == p && arr[1][1] == p && arr[2][2] == p) {
            if(comp == 1)
                    System.out.println("\nComputer won!");
                else
                    System.out.println("\nPlayer " + p + " won!");
            return 1;
        }
        if (arr[0][2] == p && arr[1][1] == p && arr[2][0] == p) {
            if(comp == 1)
                    System.out.println("\nComputer won!");
                else
                    System.out.println("\nPlayer " + p + " won!");
            return 1;
        }
        return 0;
    }


    /**
     * A method that checks the given input from the user.
     * 
     * @param input given input from the user
     * @param x lowest possible number that the user should type
     * @param y biggest possible number that the user should type
     * @return the given input as an integer
     */
    public static int inputCheck(String input, int x, int y) {
        while(input.length() == 0){
            System.out.print("Incorrect input. Please type a number between " + x + " and " + y + ": ");
            input = scanner.nextLine();
        }
        char c = input.charAt(0);
        while (input.length() != 1 || c < '0' + x || c > '0' + y) {
            System.out.print("\nIncorrect input. Please type a number between " + x + " and " + y + ": ");
            input = scanner.nextLine();
            if(input.length() != 0)
                c = input.charAt(0);
        }
        return c - '0';
    }

    /**
     * A method that checks the gameboard and plays accordingly.
     * 
     * @param arr gameboard as a matrix
     * @return the chosen cell to play
     */
    public static int hardAi(int[][] arr) {
        int out;
        int win = 0, block = 0;
        for (int x = 0; x < 3; x++) {
            if ((out = returnDifferent(arr, 3 * x + 1, 3 * x + 2, 3 * x + 3, 1)) > 0)
                block = out;
            if ((out = returnDifferent(arr, 3 * x + 1, 3 * x + 2, 3 * x + 3, 2)) > 0)
                win = out;
            if ((out = returnDifferent(arr, x + 1, x + 4, x + 7, 1)) > 0)
                block = out;
            if ((out = returnDifferent(arr, x + 1, x + 4, x + 7, 2)) > 0)
                win = out;
        }
        if ((out = returnDifferent(arr, 1, 5, 9, 1)) > 0)
            block = out;
        if ((out = returnDifferent(arr, 1, 5, 9, 2)) > 0)
            win = out;
        if ((out = returnDifferent(arr, 3, 5, 7, 1)) > 0)
            block = out;
        if ((out = returnDifferent(arr, 3, 5, 7, 2)) > 0)
            win = out;
        if(win != 0)
            return win;
        return block;
    }

    /**
     * A method for the ai of the game that returns the cell that has a different value if the other 2 have the same value.
     * 
     * @param arr gameboard as a matrix
     * @param a first cell to check
     * @param b second cell to check
     * @param c third cell to check
     * @param player current player number
     * @return the cell that has the different value, return 0 if all of them are different
     */
    public static int returnDifferent(int[][] arr, int a, int b, int c, int player) {
        int out = 0;
        if (arr[(a - 1) / 3][(a - 1) % 3] == arr[(b - 1) / 3][(b - 1) % 3] && arr[(a - 1) / 3][(a - 1) % 3] == player && arr[(c - 1) / 3][(c - 1) % 3] == 0)
            out = c;
        if (arr[(b - 1) / 3][(b - 1) % 3] == arr[(c - 1) / 3][(c - 1) % 3] && arr[(b - 1) / 3][(b - 1) % 3] == player && arr[(a - 1) / 3][(a - 1) % 3] == 0) 
            out = a;
        if (arr[(a - 1) / 3][(a - 1) % 3] == arr[(c - 1) / 3][(c - 1) % 3] && arr[(a - 1) / 3][(a - 1) % 3] == player && arr[(b - 1) / 3][(b - 1) % 3] == 0) 
            out = b;
        return out;
    }
    
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------



// ASCII Art Functions
//*****************************************************************************
//*****************************************************************************

    /**
     * A method that prints the ascii art for the main menu.
     * 
     */
    public static void asciiArt(){
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. ");
        System.out.println("| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
        System.out.println("| | _____  _____ | || |  _________   | || |   _____      | || |     ______   | || |     ____     | || | ____    ____ | || |  _________   | |");
        System.out.println("| ||_   _||_   _|| || | |_   ___  |  | || |  |_   _|     | || |   .' ___  |  | || |   .'    `.   | || ||_   \\  /   _|| || | |_   ___  |  | |");
        System.out.println("| |  | | /\\ | |  | || |   | |_  \\_|  | || |    | |       | || |  / .'   \\_|  | || |  /  .--.  \\  | || |  |   \\/   |  | || |   | |_  \\_|  | |");
        System.out.println("| |  | |/  \\| |  | || |   |  _|  _   | || |    | |   _   | || |  | |         | || |  | |    | |  | || |  | |\\  /| |  | || |   |  _|  _   | |");
        System.out.println("| |  |   /\\   |  | || |  _| |___/ |  | || |   _| |__/ |  | || |  \\ `.___.'\\  | || |  \\  `--'  /  | || | _| |_\\ /| |_ | || |  _| |___/ |  | |");
        System.out.println("| |  |__/  \\__|  | || | |_________|  | || |  |________|  | || |   `._____.'  | || |   `.____.'   | || ||_____||_____|| || | |_________|  | |");
        System.out.println("| |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
        System.out.println("| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
        System.out.println(" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
        System.out.println();
        System.out.println("  ____  _____ ____  _  __    _ __   __  __  __ _   _ ____ _____  _    _____ _         _    ____  ___ _  __    _    _   _  ");
        System.out.println(" | __ )| ____|  _ \\| |/ /   / \\\\ \\ / / |  \\/  | | | / ___|_   _|/ \\  |  ___/ \\       / \\  |  _ \\|_ _| |/ /   / \\  | \\ | | ");
        System.out.println(" |  _ \\|  _| | |_) | ' /   / _ \\\\ V /  | |\\/| | | | \\___ \\ | | / _ \\ | |_ / _ \\     / _ \\ | |_) || || ' /   / _ \\ |  \\| | ");
        System.out.println(" | |_) | |___|  _ <| . \\  / ___ \\| |   | |  | | |_| |___) || |/ ___ \\|  _/ ___ \\   / ___ \\|  _ < | || . \\  / ___ \\| |\\  | ");
        System.out.println(" |____/|_____|_| \\_\\_|\\_\\/_/   \\_\\_|   |_|  | _\\___/|____/ |_/_/   \\_\\_|/_/   \\_\\ /_/   \\_\\_| \\_\\___|_|\\_\\/_/   \\_\\_| \\_| ");
        System.out.println();
        System.out.println("  ____  _____ ____  _  _______   ____   ___   _\\/_    _    _   _ ");
        System.out.println(" | __ )| ____|  _ \\| |/ / ____| |  _ \\ / _ \\ / ___|  / \\  | \\ | |");
        System.out.println(" |  _ \\|  _| | |_) | ' /|  _|   | | | | | | | |  _  / _ \\ |  \\| |");
        System.out.println(" | |_) | |___|  _ <| . \\| |___  | |_| | |_| | |_| |/ ___ \\| |\\  |");
        System.out.println(" |____/|_____|_| \\_\\_|\\_\\_____| |____/ \\___/ \\____/_/   \\_\\_| \\_|");
        System.out.println();
        System.out.println("  _____ ____ _____   _  __    _    ____ _____  _    _   _  ___   _\\/__ _    _   _ ");
        System.out.println(" | ____/ ___| ____| | |/ /   / \\  |  _ \\_   _|/ \\  | \\ | |/ _ \\ / ___| |  | | | |");
        System.out.println(" |  _|| |  _|  _|   | ' /   / _ \\ | |_) || | / _ \\ |  \\| | | | | |  _| |  | | | |");
        System.out.println(" | |__| |_| | |___  | . \\  / ___ \\|  __/ | |/ ___ \\| |\\  | |_| | |_| | |__| |_| |");
        System.out.println(" |_____\\____|_____| |_|\\_\\/_/   \\_\\_|    |_/_/   \\_\\_| \\_|\\___/ \\____|_____\\___/ ");
        System.out.println();
        System.out.println("  __  __ _____ _   _ __  __ _____ _____   _____ _   _ _____ ____     _____   __ ");
        System.out.println(" |  \\/  | ____| | | |  \\/  | ____|_   _| | ____| \\ | | ____/ ___|   / _ \\ \\ / / ");
        System.out.println(" | |\\/| |  _| | |_| | |\\/| |  _|   | |   |  _| |  \\| |  _| \\___ \\  | | | \\ V /  ");
        System.out.println(" | |  | | |___|  _  | |  | | |___  | |   | |___| |\\  | |___ ___) | | |_| || |   ");
        System.out.println(" |_|  |_|_____|_| |_|_|  |_|_____| |_|   |_____|_| \\_|_____|____/   \\___/ |_|   ");
        System.out.println();
        System.out.println("  __  __ _____ ____  _[] ____   _   _ _____ _  ___   _ ");
        System.out.println(" |  \\/  | ____|  _ \\|_ _/ ___| (_) (_)_   _| |/ (_) (_)");
        System.out.println(" | |\\/| |  _| | |_) || | |     | | | | | | | ' /| | | |");
        System.out.println(" | |  | | |___|  _ < | | |___  | |_| | | | | . \\| |_| |");
        System.out.println(" |_|  |_|_____|_| \\_\\___\\____|  \\___/  |_| |_|\\_\\\\___/ ");
        System.out.println("                          )_)                           ");
    }

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
}
