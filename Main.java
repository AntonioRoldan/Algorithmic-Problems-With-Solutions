package CompetitiveProgrammingSolutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class PermutationsWithUniqueCharactersSolution {
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

    private List<List<String>> getArrayOfArraysOfUniqueCharactersOfEachPermutationInTheSameOrderAsPermutationsArray(ArrayList<String> arrayOfUniquePermutationsOfS){
        //We start by turning the string into a character array within the first for loop then we turn each character into a string and store it in a string array list within the second for loop and at the end of the first for loop we add it to our arrays of arrays list and empty the array of strings representing the permutation's characters
        //We iterate through our array of arrays with permutation strings splitted into strings of characters in the last for loop which is an outer for loop and make them unique in characters then add them to another array of arrays of strings. (Another array list)
        List<String> arrayOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<String>();
        ArrayList<List<String>> arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<List<String>>();
        List<List<String>> arrayOfArraysOfUniqueCharactersStringsContainedInEachIndividualPermutation = new ArrayList<List<String>>();
        char[] characterArrayOfAnIndividualPermutationSubstring = {};
        for(int i = 0; i < arrayOfUniquePermutationsOfS.size(); i++){
            characterArrayOfAnIndividualPermutationSubstring = arrayOfUniquePermutationsOfS.get(i).toCharArray();
            for(int j = 0; j < characterArrayOfAnIndividualPermutationSubstring.length; j++){
                arrayOfStringsOfAnIndividualPermutationsCharacters.add(Character.toString(characterArrayOfAnIndividualPermutationSubstring[j]));
            }
            arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.add(arrayOfStringsOfAnIndividualPermutationsCharacters);
            arrayOfStringsOfAnIndividualPermutationsCharacters = new ArrayList<String>();
        }
        //print(arrayOfArraysOfStringsOfAnIndividualPermutationSubstringsCharacters); //We print the arrays of strings of characters of each permutation substring that we have just calculated
        for(int i = 0; i < arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.size(); i++){
            arrayOfStringsOfAnIndividualPermutationsCharacters = new HashSet<String>(arrayOfArraysOfStringsOfAnIndividualPermutationsCharacters.get(i)).stream().toList(); //This time the variable we are filling is set to a list of unique characters strings of an individual's permutation's substring
            arrayOfArraysOfUniqueCharactersStringsContainedInEachIndividualPermutation.add(arrayOfStringsOfAnIndividualPermutationsCharacters);
        }
        print(arrayOfArraysOfUniqueCharactersStringsContainedInEachIndividualPermutation);
        return arrayOfArraysOfUniqueCharactersStringsContainedInEachIndividualPermutation;
    }

    private List<String> getPermutationsWithUniqueCharacters(List<List<String>> arrayOfArraysOfUniqueCharactersWithinEachPermutationInTheSameOrderAsPermutationsArray, List<String> arrayOfUniquePermutationsOfS){
        List<String> arrayOfPermutationsWithUniqueCharactersOfS = new ArrayList<String>();
        String joinedPermutationString = "";
        String tempContainerForStringToBeJoinedToPermutationString = "";
        for(int i = 0; i < arrayOfArraysOfUniqueCharactersWithinEachPermutationInTheSameOrderAsPermutationsArray.size(); i++){ //Since both arrays elements are in the same order with a perfect one-to-one match between them we iterate through one of the arrays and use its indices with the other one
            for(int j = 0; j < arrayOfArraysOfUniqueCharactersWithinEachPermutationInTheSameOrderAsPermutationsArray.get(i).size(); j++){
                joinedPermutationString = String.join("", arrayOfArraysOfUniqueCharactersWithinEachPermutationInTheSameOrderAsPermutationsArray.get(i));
            }
            arrayOfPermutationsWithUniqueCharactersOfS.add(joinedPermutationString);
            joinedPermutationString = "";
        }
        print(arrayOfPermutationsWithUniqueCharactersOfS);
        return arrayOfPermutationsWithUniqueCharactersOfS;
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
        print(arrayOfPermutationsOfS);
        return arrayOfUniquePermutationsOfS;
    }

    public List<String> lengthOfLongestSubstring(String s) {
        //Find permutations of a string
        ArrayList<String> arrayOfUniquePermutationsOfSWithoutDuplicates = new ArrayList<>();
        List<List<String>> arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS = new ArrayList<List<String>>();
        arrayOfUniquePermutationsOfSWithoutDuplicates = this.getPermutationsOfString(s);
        print(arrayOfUniquePermutationsOfSWithoutDuplicates);
        arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS = this.getArrayOfArraysOfUniqueCharactersOfEachPermutationInTheSameOrderAsPermutationsArray(arrayOfUniquePermutationsOfSWithoutDuplicates);
        return this.getPermutationsWithUniqueCharacters(arrayOfArraysOfUniqueCharactersInEachPermutationOfStringS, arrayOfUniquePermutationsOfSWithoutDuplicates);
    }
}
