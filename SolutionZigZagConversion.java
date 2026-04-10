package CompetitiveProgrammingSolutions;

import java.util.*;

public class SolutionZigZagConversion
{
        private <T> void print(T elementToBePrinted){

            System.out.println(elementToBePrinted);
        }

        private List<String> getZigZagPattern(String string, int numOfRows){
            List<String> stringCharactersOfSplittedArray = Arrays.asList(string.split(""));
            List<String> columnOrZigZagElementContainer = new ArrayList<String>();
            print(stringCharactersOfSplittedArray); //So we store odds or evens then add them before adding the next iterated elements then add the next iterated elements in the iteration of the next iterated element in the previous one we don't add it but store it for the next iterated element which is the one we are defining in this line
            List<Object> repeatedColumnsAndDiagonals = new ArrayList<>();
            List<Object> columnsAndDiagonals = new ArrayList<>();
            Boolean addReservedCharacterStringSoLastElementOfColumnIsFirstOfDiagonalAndViceversa = false;
            int elementsCountUntilItReachesNumOfRows = 0;
            for (int i = 0; i < stringCharactersOfSplittedArray.size(); i++) {
                // Expand the window
                columnOrZigZagElementContainer.add(stringCharactersOfSplittedArray.get(i));

                // Once window size is reached
                if (i >= numOfRows - 1) {
                    repeatedColumnsAndDiagonals.add(((ArrayList<String>) columnOrZigZagElementContainer).clone());
                    columnOrZigZagElementContainer.remove(stringCharactersOfSplittedArray.get(i - numOfRows + 1));
                    print(columnOrZigZagElementContainer);
                }
            }
            columnsAndDiagonals = Arrays.asList(new LinkedHashSet<>(repeatedColumnsAndDiagonals));
            print(columnsAndDiagonals);
            return stringCharactersOfSplittedArray;
        }

        public String convert(String s, int numRows) {
            this.getZigZagPattern(s, numRows);
            return "";
        }
}
