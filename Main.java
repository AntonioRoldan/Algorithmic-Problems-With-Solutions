import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


//PROBLEM STATEMENT: RETURN AMOUNT OF UNIQUE CHARACTERS IN PERMUTATION OR PERMUTATIONS WITH LARGEST AMOUNT OF UNIQUE CHARACTERS!

class Solution {
    private String swapCharactersWithinString(String substringInOrder, int i, int j){
        char ch[] = substringInOrder.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }

    //We cannot use a hashset here because that would just remove repeated occurrences of characters rather than strings with repeated occurrences of characters

    private <T> void print(T elementToBePrinted){
        System.out.println(elementToBePrinted);
    }

    private <T> int getLengthOfLongestArrayInArrayOfArrays(List<List<T>> arrayOfArrays) {
        ArrayList<Integer> lengthsOfArrayWithinArrayInTheSameOrderAsArraysWithinArray = new ArrayList<Integer>();
        for(int i = 0; i < arrayOfArrays.size(); i++){
            lengthsOfArrayWithinArrayInTheSameOrderAsArraysWithinArray.add(arrayOfArrays.get(i).size());
        }
        return Collections.max(lengthsOfArrayWithinArrayInTheSameOrderAsArraysWithinArray);
    }

    private List<List<String>> getArrayOfArraysOfEachPermutationWithItsUniqueCharacters(ArrayList<String> arrayOfUniqueSubstringsOfS){
        //We start by turning the string into a character array within the first for loop then we turn each character into a string and store it in a string array list within the second for loop and at the end of the first for loop we add it to our arrays of arrays list and empty the array of strings representing the permutation's characters
        //We iterate through our array of arrays with permutation strings splitted into strings of characters in the last for loop which is an outer for loop and make them unique in characters then add them to another array of arrays of strings. (Another array list)
        List<String> arrayOfStringsOfAnIndividualPermutationSubstringsCharacters = new ArrayList<String>();
        ArrayList<List<String>> arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters = new ArrayList<List<String>>();
        List<List<String>> arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutationSubstring = new ArrayList<List<String>>();
        char[] characterArrayOfAnIndividualPermutationSubstring = {};
        for(int i = 0; i < arrayOfUniqueSubstringsOfS.size(); i++){
            characterArrayOfAnIndividualPermutationSubstring = arrayOfUniqueSubstringsOfS.get(i).toCharArray();
            for(int j = 0; j < characterArrayOfAnIndividualPermutationSubstring.length; j++){
                arrayOfStringsOfAnIndividualPermutationSubstringsCharacters.add(Character.toString(characterArrayOfAnIndividualPermutationSubstring[j]));
            }
            arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters.add(arrayOfStringsOfAnIndividualPermutationSubstringsCharacters);
            arrayOfStringsOfAnIndividualPermutationSubstringsCharacters = new ArrayList<String>();
        }
        //print(arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters); //We print the arrays of strings of characters of each permutation substring that we have just calculated
        for(int i = 0; i < arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters.size(); i++){
            arrayOfStringsOfAnIndividualPermutationSubstringsCharacters = new HashSet<String>(arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters.get(i)).stream().toList(); //This time the variable we are filling is set to a list of unique characters strings of an individual's permutation's substring
            arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutationSubstring.add(arrayOfStringsOfAnIndividualPermutationSubstringsCharacters);
        }
        //print(arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutationSubstring);
        return arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutationSubstring;
    }

    private ArrayList<String> getPermutationsOfString(String s){
        ArrayList<String> arrayOfSubstringsOfS = new ArrayList<String>();
        ArrayList<String> arrayOfSmallerPermutationSubstringsOfS = new ArrayList<String>();
        ArrayList<String> arrayOfUniqueSubstringsOfS = new ArrayList<>();
        int combinationsAmountOfElementsCurrentlySearching = 2;
        int amountWeAreAccumulatingCount = 1;
        int indexOfCurrentlyIteratedAtSubstringOfS = 0;
        String smallerSizePermutationSubstringOfSContainer = "";
        for(int i = 0; i < s.length(); i++){ //We find permutations iterating through each character and then within our inner loop we sap it for every other character within our substring except by itself
            for(int j = 0; j < s.length(); j++){
                if(i == j){
                    continue;
                }
                arrayOfSubstringsOfS.add(this.swapCharactersWithinString(s, i, j));
            }//Abcd
        } //Now because we know that the first three elements of any given set of total permutations will give us all permutations of three characters (the characters that our permutations are made of) we iterate through our array of substrings
        //then through each string and then we extract its substrings of an ever increasing length from zero up to the total length of the string. incremmenting by one in our last third for loop and at the end of the second we add it and empty our container
        //Then we join our two arrays, make their joined array unique and return it.
        for(int i = 0; i < arrayOfSubstringsOfS.size(); i++) {
            for (int j = 1; j < s.length(); j++) {
                for (int k = 0; k < j; k++) {
                    smallerSizePermutationSubstringOfSContainer += Character.toString(arrayOfSubstringsOfS.get(i).charAt(k));
                }
                arrayOfSmallerPermutationSubstringsOfS.add(smallerSizePermutationSubstringOfSContainer);
                smallerSizePermutationSubstringOfSContainer = "";
            }
        }
        arrayOfSubstringsOfS.addAll(arrayOfSmallerPermutationSubstringsOfS);
        //Now we have just calculated above the permutations of substrings of the same length as the original string, their smaller substrings constitute the remaining permutations
        arrayOfUniqueSubstringsOfS = new ArrayList<String>(new HashSet<String>(arrayOfSubstringsOfS)); //We then delete occurrences

        return arrayOfUniqueSubstringsOfS;
    }

    public int lengthOfLongestSubstring(String s) {
        //Find permutations of a string
        ArrayList<String> arrayOfUniqueSubstringsOfS = new ArrayList<>();
        List<List<String>> arrayOfArraysOfUniqueSubstringsOfStringOfUniqueCharactersInS = new ArrayList<List<String>>();
        String stringOfUniqueCharactersInS = "";
        arrayOfUniqueSubstringsOfS = this.getPermutationsOfString(s);
        print(arrayOfUniqueSubstringsOfS.size());
        arrayOfArraysOfUniqueSubstringsOfStringOfUniqueCharactersInS = this.getArrayOfArraysOfEachPermutationWithItsUniqueCharacters(arrayOfUniqueSubstringsOfS);
        print(arrayOfArraysOfUniqueSubstringsOfStringOfUniqueCharactersInS);

        return getLengthOfLongestArrayInArrayOfArrays(arrayOfArraysOfUniqueSubstringsOfStringOfUniqueCharactersInS);
    }
}

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("qwerttqwerwteq"));

    }
}

