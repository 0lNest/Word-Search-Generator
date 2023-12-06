
// Programmer: Oleksandr Nesterov
// Class: CS &145 Face-to-face
// Date: 17/10/2023
// Sources: GeeksforGeeks, Github
// Assignment 1: Word Search Generator
// Purpose: Learn
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
public class WordSearchGenerator {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
char[][] wordSearch = null;
System.out.println("Welcome to the Word Search Generator!");
while (true) {
System.out.println("Menu:");
System.out.println("g - Generate a new word search");
System.out.println("p - Print the word search");
System.out.println("s - Show the solution");
System.out.println("q - Quit");
System.out.print("Enter your choice: ");
String choice = scanner.nextLine();
if (choice.equals("g")) {
wordSearch = generateWordSearch(); // Calls the function to
generate a word search.
} else if (choice.equals("p")) {
if (wordSearch != null) {
printWordSearch(wordSearch); // Calls the function to print the
word search.
} else {
System.out.println("Generate a word search first.");
}
} else if (choice.equals("s")) {
if (wordSearch != null) {
printSolution(wordSearch); // Calls the function to print the
solution.
} else {
System.out.println("Generate a word search first.");
}
} else if (choice.equals("q")) {
break; // Exits the program.
} else {
System.out.println("Invalid choice. Please try again.");
}
}
}
public static char[][] generateWordSearch() {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the number of words: ");
int numWords = Integer.parseInt(scanner.nextLine());
String[] words = new String[numWords];
for (int i = 0; i < numWords; i++) {
System.out.print("Enter word " + (i + 1) + ": ");
words[i] = scanner.nextLine().toUpperCase(); // Collects words from the
user.
}
int size = Math.max(findMaxLength(words) + 2, 15);
char[][] wordSearch = createEmptyWordSearch(size); // Creates an empty word
search grid.
// Initialize the WordSearchPuzzle object
WordSearchPuzzle puzzle = new WordSearchPuzzle();
for (String word : words) {
if (puzzle.wordExists(wordSearch, word)) {
placeWord(wordSearch, word); // Places each word in the grid.
} else {
System.out.println("Word " + word + " cannot be placed in the
puzzle.");
}
}
fillEmptySpaces(wordSearch); // Fills empty spaces with random letters.
return wordSearch; // Returns the generated word search.
}
public static int findMaxLength(String[] words) {
int maxLength = 0;
for (String word : words) {
if (word.length() > maxLength) {
maxLength = word.length(); // Finds the length of the longest word.
}
}
return maxLength;
}
public static char[][] createEmptyWordSearch(int size) {
char[][] wordSearch = new char[size][size];
for (int i = 0; i < size; i++) {
for (int j = 0; j < size; j++) {
wordSearch[i][j] = ' '; // Creates an empty word search grid with
spaces.
}
}
return wordSearch;
}
public static void placeWord(char[][] wordSearch, String word) {
Random random = new Random();
int tries = 0;
while (tries < 100) {
int direction = random.nextInt(3);
int row, col;
if (direction == 0) { // Horizontal
row = random.nextInt(wordSearch.length);
col = random.nextInt(wordSearch.length - word.length() + 1);
} else if (direction == 1) { // Vertical
row = random.nextInt(wordSearch.length - word.length() + 1);
col = random.nextInt(wordSearch.length);
} else { // Diagonal
row = random.nextInt(wordSearch.length - word.length() + 1);
col = random.nextInt(wordSearch.length - word.length() + 1);
}
if (canPlaceWord(wordSearch, word, row, col, direction)) {
placeWordInDirection(wordSearch, word, row, col, direction); //
Places a word in a specific direction.
break;
}
tries++;
}
}
public static boolean canPlaceWord(char[][] wordSearch, String word, int row,
int col, int direction) {
for (int i = 0; i < word.length(); i++) {
char cell = wordSearch[row][col];
if (cell != ' ' && cell != word.charAt(i)) {
return false;
}
if (direction == 0) { // Horizontal
col++;
} else if (direction == 1) { // Vertical
row++;
} else { // Diagonal
row++;
col++;
}
}
return true;
}
public static void placeWordInDirection(char[][] wordSearch, String word, int
row, int col, int direction) {
for (int i = 0; i < word.length(); i++) {
wordSearch[row][col] = word.charAt(i);
if (direction == 0) { // Horizontal
col++;
} else if (direction == 1) { // Vertical
row++;
} else { // Diagonal
row++;
col++;
}
}
}
public static void fillEmptySpaces(char[][] wordSearch) {
Random random = new Random();
for (int i = 0; i < wordSearch.length; i++) {
for (int j = 0; j < wordSearch[i].length; j++) {
if (wordSearch[i][j] == ' ') {
wordSearch[i][j] = (char) (random.nextInt(26) + 'A'); // Fills
empty spaces with random letters.
}
}
}
}
public static void printWordSearch(char[][] wordSearch) {
for (int i = 0; i < wordSearch.length; i++) {
for (int j = 0; j < wordSearch[i].length; j++) {
System.out.print(wordSearch[i][j] + " "); // Prints the word
search.
}
System.out.println();
}
System.out.println();
}
public static void printSolution(char[][] wordSearch) {
for (int i = 0; i < wordSearch.length; i++) {
for (int j = 0; j < wordSearch[i].length; j++) {
if (wordSearch[i][j] == ' ') {
System.out.print('X' + " "); // Prints the solution with 'X'
for hidden letters.
} else {
System.out.print(wordSearch[i][j] + " ");
}
}
System.out.println();
}
}
}
