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



//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------


// Matrix Functions
//*****************************************************************************
//*****************************************************************************



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
