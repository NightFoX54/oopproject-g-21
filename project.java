import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a class for our Object Oriented Programming course project.
*/
public class project {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor for the {@code project} class.
     */
    public project() {
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
        while(input.length() == 0){
            System.out.print("Incorrect input, please type again: ");
            input = scanner.nextLine();
        }
        switch(selection){
            case 1:
                char check = input.charAt(0);
                while(input.length() != 1 || check < 'A' || check > 'E'){
                    System.out.print("Incorrect input, please type again: ");
                    input = scanner.nextLine();
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
                                
                                if(current < '0' || current > '9')
                                    correctInput = 0;
                            }
                            for(int i = 0; i < y.length(); i++){
                                current = y.charAt(i);
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
                    if(correctInput == 0){
                        System.out.print("Incorrect input, please type again or type 'X' to go back to previous menu: ");
                        input = scanner.nextLine();
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
        long number2 = (long)Math.abs(number);
        int digit = 0;
        while(number2 > 0){
            number2 /= 10;
            digit++;
        }
        if(number == 0)
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



//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// TicTacToe Functions
//*****************************************************************************
//*****************************************************************************



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
