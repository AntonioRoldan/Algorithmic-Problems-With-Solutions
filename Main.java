import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


//PROBLEM STATEMENT: FIND ALL PERMUTATIONS OF S AND RETURN LIST WITH THE UNIQUE CHARACTERS EACH PERMUTATION HAS

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
        List<String> arrayOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<String>();
        ArrayList<List<String>> arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<List<String>>();
        List<List<String>> arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutation = new ArrayList<List<String>>();
        char[] characterArrayOfAnIndividualPermutationSubstring = {};
        for(int i = 0; i < arrayOfUniqueSubstringsOfS.size(); i++){
            characterArrayOfAnIndividualPermutationSubstring = arrayOfUniqueSubstringsOfS.get(i).toCharArray();
            for(int j = 0; j < characterArrayOfAnIndividualPermutationSubstring.length; j++){
                arrayOfStringsOfAnIndividualPermutationsCharacters.add(Character.toString(characterArrayOfAnIndividualPermutationSubstring[j]));
            }
            arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.add(arrayOfStringsOfAnIndividualPermutationsCharacters);
            arrayOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<String>();
        }
        //print(arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters); //We print the arrays of strings of characters of each permutation substring that we have just calculated
        for(int i = 0; i < arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.size(); i++){
            arrayOfStringsOfAnIndividualPermutationsCharacters = new HashSet<String>(arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.get(i)).stream().toList(); //This time the variable we are filling is set to a list of unique characters strings of an individual's permutation's substring
            arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutation.add(arrayOfStringsOfAnIndividualPermutationsCharacters);
        }
        //print(arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutationSubstring);
        return arrayOfArraysOfUniqueCharactersStringsContainedInAnIndividualPermutation;
    }

    private ArrayList<String> getPermutationsOfString(String s){
        ArrayList<String> arrayOfPermutationsOfS = new ArrayList<String>();
        ArrayList<String> arrayOfSmallerPermutationsOfS = new ArrayList<String>();
        ArrayList<String> arrayOfUniquePermutationsOfS = new ArrayList<>();
        int combinationsAmountOfElementsCurrentlySearching = 2;
        int amountWeAreAccumulatingCount = 1;
        int indexOfCurrentlyIteratedAtSubstringOfS = 0;
        String smallerSizePermutationSubstringOfSContainer = "";
        for(int i = 0; i < s.length(); i++){ //We find permutations iterating through each character and then within our inner loop we sap it for every other character within our substring except by itself
            for(int j = 0; j < s.length(); j++){
                if(i == j){
                    continue;
                }
                arrayOfPermutationsOfS.add(this.swapCharactersWithinString(s, i, j));
            }//Abcd
        } //Now because we know that the first three elements of any given set of total permutations will give us all permutations of three characters (the characters that our permutations are made of) we iterate through our array of substrings
        //then through each string and then we extract its substrings of an ever increasing length from zero up to the total length of the string. incremmenting by one in our last third for loop and at the end of the second we add it and empty our container
        //Then we join our two arrays, make their joined array unique and return it.
        for(int i = 0; i < arrayOfPermutationsOfS.size(); i++) {
            for (int j = 1; j < s.length(); j++) {
                for (int k = 0; k < j; k++) {
                    smallerSizePermutationSubstringOfSContainer += Character.toString(arrayOfPermutationsOfS.get(i).charAt(k));
                }
                arrayOfSmallerPermutationsOfS.add(smallerSizePermutationSubstringOfSContainer);
                smallerSizePermutationSubstringOfSContainer = "";
            }
        }
        arrayOfPermutationsOfS.addAll(arrayOfSmallerPermutationsOfS);
        //Now we have just calculated above the permutations of substrings of the same length as the original string, their smaller substrings constitute the remaining permutations
        arrayOfUniquePermutationsOfS = new ArrayList<String>(new HashSet<String>(arrayOfPermutationsOfS)); //We then delete occurrences

        return arrayOfUniquePermutationsOfS;
    }

    public List<List<String>> lengthOfLongestSubstring(String s) {
        //Find permutations of a string
        ArrayList<String> arrayOfUniquePermutationsOfSWithoutDuplicates = new ArrayList<>();
        List<List<String>> arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS = new ArrayList<List<String>>();
        arrayOfUniquePermutationsOfSWithoutDuplicates = this.getPermutationsOfString(s);
        print(arrayOfUniquePermutationsOfSWithoutDuplicates);
        arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS = this.getArrayOfArraysOfEachPermutationWithItsUniqueCharacters(arrayOfUniquePermutationsOfSWithoutDuplicates);
        return arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS;
    }
}

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcd"));

    }
}

