package misc;

import java.util.HashMap;
import java.util.Set;

/**
 * Formats information of Employee objects within an AggregateEmployeeRecord
 */
class FormatOutputText {
    private static AggregateEmployeeRecord recordOfAllEmployees = new AggregateEmployeeRecord();

    /**
     * Prepares the final aggregate employee records
     *
     * @param combinedRecord AggregateEmployeeRecord object that holds all the information to be formatted
     * @return aggregateRecordsText The compiled and formatted employee records in one long text
     */
    static String getFinalFormattedText(AggregateEmployeeRecord combinedRecord){
        StringBuffer finalText = new StringBuffer();
        recordOfAllEmployees = combinedRecord;

        finalText.append(combinedRecord.getFormattedEmployeeRecordsText());

        finalText.append(combinedRecord.getAverageAgeText());
        finalText.append(combinedRecord.getAveragePaymentTypeText("Commission"));
        finalText.append(combinedRecord.getAveragePaymentTypeText("Hourly"));
        finalText.append(combinedRecord.getAveragePaymentTypeText("Salary"));

        finalText.append(getRedundantText("first name"));
        finalText.append(getRedundantText("last name"));

        String aggregateRecordsText = finalText.toString();
        return aggregateRecordsText;
    }

    /**
     * Get text describing repetition of typeOfText
     *
     * @param typeOfText Type of text to counted
     * @return repetitionText Text containing all information on repetition of typeOfText
     */
    private static String getRedundantText(String typeOfText){
        StringBuffer outputText = new StringBuffer();
        String emptyString = "";

        outputText.append(getRedundancyHeader(typeOfText));

        if(typeOfText.equals("first name"))
        {
            outputText.append(getRedundantFirstNameText());
        }
        else if(typeOfText.equals("last name")){
            outputText.append(getRedundantLastNameText());
        } else{
            System.err.println("Cannot count redundancy for text: " + typeOfText);
            return emptyString;
        }

        return outputText.toString();
    }

    /**
     * Prints header for the typeOfText
     *
     * @param typeOfText Text to add into header
     * @return formattedText Formatted header
     */
    private static String getRedundancyHeader(String typeOfText){
        typeOfText = typeOfText.substring(0, 1).toUpperCase() + typeOfText.substring(1);
        String formattedText = String.format("\n%ss with more than one person sharing it:\n", typeOfText);
        return formattedText;
    }

    /**
     * Counts all first names and prints the number of repetitions
     *
     * @return outputText Text describing all repetitions of first names
     */
    private static String getRedundantFirstNameText(){
        String outputText;

        HashMap<String, Integer> redundantTextCount = getFirstNameHashMap();

        if(redundantTextCount.isEmpty()) {
            outputText = "All first names are unique";
        } else {
            outputText = getRepetitionsText(redundantTextCount);
        }

        return outputText;
    }

    /**
     * Stores all first names as keys in a HashMap. If they repeat their value increases
     *
     * @return redundancyCount HashMap containing the first names as keys and their number of appearances as values
     */
    private static HashMap<String, Integer> getFirstNameHashMap(){
        HashMap<String, Integer> redundancyCount = new HashMap<String, Integer>();
        Employee individualEmployee;

        int numberOfOccurrences = 0;
        for(int i = 0; i < recordOfAllEmployees.getNumberOfEmployees(); i++) {
            individualEmployee = recordOfAllEmployees.getGroupOfEmployees()[i];
            if(redundancyCount.containsKey(individualEmployee.getFirstName())) {
                redundancyCount.put(individualEmployee.getFirstName(), redundancyCount.get(individualEmployee.getFirstName()) + 1);
                numberOfOccurrences++;
            } else {
                redundancyCount.put(individualEmployee.getFirstName(), 1);
            }
        }

        if(numberOfOccurrences <= 1){
            redundancyCount.clear();
            return redundancyCount;
        }

        return redundancyCount;
    }

    /**
     * Prints a name if they appear more than once
     *
     * @param redundancyCount HashMap containing all types of names to be printed
     * @return Formatted text containing all repeating names
     */
    private static String getRepetitionsText(HashMap<String, Integer> redundancyCount){
        StringBuffer outputText = new StringBuffer();
        Set<String> setOfText = redundancyCount.keySet();
        for(String item : setOfText) {
            if(redundancyCount.get(item) > 1) {
                outputText.append(String.format("%s, # people with this name: %d\n", item, redundancyCount.get(item)));
            }
        }
        return outputText.toString();
    }

    /**
     * Counts all last names and prints the number of repetitions
     *
     * @return outputText Text describing all repetitions of last names
     */
    private static String getRedundantLastNameText(){
        String outputText;

        HashMap<String, Integer> redundantTextCount = getLastNameHashMap();

        if(redundantTextCount.isEmpty()) {
            outputText = "All last names are unique";
        } else {
            outputText = getRepetitionsText(redundantTextCount);
        }

        return outputText;
    }

    /**
     * Stores all last names as keys in a HashMap. If they repeat their value increases
     *
     * @return redundancyCount HashMap containing the last names as keys and their number of appearances as values
     */
    private static HashMap<String, Integer> getLastNameHashMap(){
        HashMap<String, Integer> redundancyCount = new HashMap<String, Integer>();
        Employee individualEmployee;

        int numberOfOccurrences = 0;
        for(int i = 0; i < recordOfAllEmployees.getNumberOfEmployees(); i++) {
            individualEmployee = recordOfAllEmployees.getGroupOfEmployees()[i];
            if(redundancyCount.containsKey(individualEmployee.getLastName())) {
                redundancyCount.put(individualEmployee.getLastName(), redundancyCount.get(individualEmployee.getLastName()) + 1);
                numberOfOccurrences++;
            } else {
                redundancyCount.put(individualEmployee.getLastName(), 1);
            }
        }

        if(numberOfOccurrences <= 1){
            redundancyCount.clear();
            return redundancyCount;
        }

        return redundancyCount;
    }
}
