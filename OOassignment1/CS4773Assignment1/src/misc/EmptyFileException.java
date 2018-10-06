package misc;

/**
 * File read had no contents
 */
public class EmptyFileException extends Exception {
    /**
     * This method will print that the file is empty
     */
    public EmptyFileException(){
        System.err.println("File is Empty");
    }
}
