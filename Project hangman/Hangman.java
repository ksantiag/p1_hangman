import java.io.*;
import java.util.*;

public class Hangman
{
   public static void printParts(int g5)
   {
      if(g5 == 5)
         {
            System.out.println("---");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("-----");   
         }
         else if(g5 == 4)
         {
            System.out.println("---");
            System.out.println("| O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("-----"); 
         }
         else if(g5 == 3)
         {
            System.out.println("---");
            System.out.println("| O");
            System.out.println("| |");
            System.out.println("|");
            System.out.println("-----");
         }
         else if(g5 ==2)
         {
            System.out.println("---");
            System.out.println("| O");
            System.out.println("| |");
            System.out.println("|/ \\");
            System.out.println("-----");   
         }
         else if(g5 == 1)
         {
            System.out.println("---");
            System.out.println("| O");
            System.out.println("| |~");
            System.out.println("|/ \\");
            System.out.println("-----"); 
         }
         else
         {
            System.out.println("---");
            System.out.println("| O");
            System.out.println("|~|~");
            System.out.println("|/ \\");
            System.out.println("-----"); 
         }
   }
   public static void main(String[] args)
   {
      
      //Load Words
      String[] wordsArr = loadWords();
      //Select a random word from the wordslist
      String randomWord = wordsArr[(int)(Math.random()*wordsArr.length)-1];
      //send random word to method and get it back as a char []
      char[] secretArr = stringToChar(randomWord);
      //print inital welcome statement
      System.out.println("      █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
      System.out.println("      █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█");
      System.out.println("      █░░║║║╠─║─║─║║║║║╠─░░█");
      System.out.println("      █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█");
      System.out.println("      █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
      System.out.println();
      System.out.println("              ╔╦╗╔═╗");
      System.out.println("               ║ ║ ║");
      System.out.println("               ╩ ╚═╝");
      System.out.println();
      System.out.println("      ╦ ╦╔═╗╔╗╔╔═╗╔╦╗╔═╗╔╗╔");
      System.out.println("      ╠═╣╠═╣║║║║ ╦║║║╠═╣║║║");
      System.out.println("      ╩ ╩╩ ╩╝╚╝╚═╝╩ ╩╩ ╩╝╚╝");      
      System.out.println();                                                          
      System.out.println("I am thinking of a word that is "+secretArr.length+" letters long.");
      System.out.println("-------------");
      //Created char[] answer to hold the correct guess filled with'-' to start. 
      char[] answer = new char[secretArr.length];
      //fills answer
      for(int i = 0; i < answer.length; i++)
      {
         answer[i] = '-';
      }
      //letterPool created to hold guesses
      //and to check if they are repeated set to a hundred as an insurance policy
      //shouldnt take a hundred guess to win or lose.
      char[] letterPool = new char[100];
      
      //One of the continuing conditions
      boolean gameOver = false;
      //Second continuing condition
      int g5 = 5;
      
      char guess;
      //get user input
      Scanner input = new Scanner(System.in);
      
      printParts(g5);
      
      do{
         
         //if else used to change from multiple guesses to one guess
         if(g5 > 1)
         {
            System.out.println("You have "+g5+" guesses left.");
            System.out.println();
            
         }   
         else
         {
            System.out.println("You have "+g5+" guess left.");
            System.out.println();
         }   
               
         //gets first char in 0th position for next input as nextChar doesnt exist 
         System.out.print("Please guess letter: ");
         guess = input.next().charAt(0);
         System.out.println();
             
         //Sent to a void printOpps method to check if letter has already been guess and prints a string.
         printOops(letterPool, guess, answer); 
         
         //compares new guess vs letter pool
         boolean inPool = isInPool(letterPool, guess, g5);
         //checks to see if guess is in secret word.
         boolean goodGuess = isInSecretWord(secretArr, answer, guess);
         
         if(goodGuess == true && inPool == false)
         {
            System.out.print("Good guess: ");
            
            for(int i = 0; i < answer.length; i++)
            {
               System.out.print(answer[i]);
            }
            System.out.println();
            
            int  countdash = 0;
            for(int i = 0; i < answer.length; i++)
            {
               if(answer[i] == '-')
               {
                  countdash++;   
               }  
            } 
            if(countdash == 0)
            {
               gameOver = true;
            }
           
         }
         else if(inPool == false)
         {
            System.out.print("Opps! That letter is not in my word: ");
            for(int i = 0; i < answer.length; i++)
            {
               System.out.print(answer[i]);
            }
            System.out.println();
            g5--;
         }
          
         System.out.println("-------------");
         
         
         printParts(g5);
             
      }while(g5 > 0 && gameOver == false);   
      
      if(g5 == 0)
      {
         System.out.print("Sorry, you ran out of guesses. The word was ");
         
         for(int i = 0; i < secretArr.length; i++)
            {
               System.out.print(secretArr[i]+" ");
            }
            System.out.print(".");
            System.out.println();
            
            System.out.println("░░░░░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░░░░░░");
            System.out.println("░░░░░░░░░░░░▄████████████████▄░░░░░░░░░░");
            System.out.println("░░░░░░░░░░▄██▀░░░░░░░▀▀████████▄░░░░░░░░");
            System.out.println("░░░░░░░░░▄█▀░░░░░░░░░░░░░▀▀██████▄░░░░░░");
            System.out.println("░░░░░░░░░███▄░░░░░░░░░░░░░░░▀██████░░░░░");                        System.out.println("░░░░░░░░▄░░▀▀█░░░░░░░░░░░░░░░░██████░░░░");
            System.out.println("░░░░░░░█▄██▀▄░░░░░▄███▄▄░░░░░░███████░░░");
            System.out.println("░░░░░░▄▀▀▀██▀░░░░░▄▄▄░░▀█░░░░█████████░░");
            System.out.println("░░░░░▄▀░░░░▄▀░▄░░█▄██▀▄░░░░░██████████░░");
            System.out.println("░░░░░█░░░░▀░░░█░░░▀▀▀▀▀░░░░░██████████▄░");
            System.out.println("░░░░░░░▄█▄░░░░░▄░░░░░░░░░░░░██████████▀░");
            System.out.println("░░░░░░█▀░░░░▀▀░░░░░░░░░░░░░███▀███████░░");
            System.out.println("░░░▄▄░▀░▄░░░░░░░░░░░░░░░░░░▀░░░██████░░░");
            System.out.println("██████░░█▄█▀░▄░░██░░░░░░░░░░░█▄█████▀░░░");
            System.out.println("██████░░░▀████▀░▀░░░░░░░░░░░▄▀█████████▄");
            System.out.println("██████░░░░░░░░░░░░░░░░░░░░▀▄████████████");
            System.out.println("██████░░▄░░░░░░░░░░░░░▄░░░██████████████");
            System.out.println("██████░░░░░░░░░░░░░▄█▀░░▄███████████████");
            System.out.println("███████▄▄░░░░░░░░░▀░░░▄▀▄███████████████");
      }
      else
      {
         System.out.print("Congratulations, you've won!");

      } 
      
      
      
      
      //while loop to keep in loop until they guess right or lose all guesses
      //send to pool method to check if in there if in there send opps message
      
      //check if its in secret word
      //if not deducted guess
      //if in there update array             
   }
   public static boolean isInPool(char[] letterPool, char guess, int g5)
   {
      boolean result = false, foundNull = false; 
      
      
      for(int i = 0; i < letterPool.length; i++)
      {
         if(guess == letterPool[i])
            result = true; 
              
      }
      
      int count = 0;
      
      while(foundNull == false)
      {
         if(letterPool[count] == '\u0000')
         {
            letterPool[count] = guess;
            foundNull = true; 
         }
         count++;
         
      }
      
      return result;
    
   }
   public static void printOops(char[] pool, char guess, char[] answer)
   {
      boolean result = false; 
      for(int i = 0; i < pool.length; i++)
      {
         if(guess == pool[i])
         {
            result = true; 
         }     
      }
      if(result == true)
      {
         System.out.print("oops! you've already guessed that letter: ");
          for(int i = 0; i < answer.length; i++)
            {
               System.out.print(answer[i]);
            }
            System.out.println();
      }  
   }
   public static boolean isInSecretWord(char[]secretArr, char[]answer, char guess)
   {
     boolean result = false;
     int count = 0; 
     
     for(int i = 0; i < secretArr.length; i++)
        {
          if(guess == secretArr[i])
          {
            answer[i] = guess; 
            count++;    
          }
        }
        if(count > 0)
        {
         result = true;
        }
        return result;  
   }
     
   	/* Helper Code -----------------------------------------------
   	* You do not have to understand the provided helper methods
   	* But you will have to know how/when to call these methods
   	* Make sure to read the instructions
   	* DO NOT make any changes to the methods below UNLESS specified
   	* by the directions.
   	*/
      
   	public static String[] loadWords()
   	{
    	/*
   		* Returns a String array of valid words.
   		* Also prints out the total number of words (Strings) in the array.
   		*/
   
      	ArrayList<String> wordList = new ArrayList<String>();
      	File f = new File("words.txt");
      	String[] wordsArr = new String[wordList.size()];
      	try
      	{
         	Scanner in = new Scanner(f);
         	while(in.hasNext())
         	{
            	String word = in.next();
            	wordList.add(word);
         	}
         	in.close(); 
         	System.out.println("Loading words from the file......");
         	System.out.println(wordList.size()+" words loaded.");
         	System.out.println("-------------");
         	wordsArr = (String[])wordList.toArray(wordsArr);
      	} catch (FileNotFoundException ex) {
         	System.out.println("File not found.");
      	}
      	return wordsArr;
   	}
   
   	public static char[] stringToChar(String secretWord)
   	{
   		/**
   		* takes a string which is a secretWord
   		* Returns a char array of secretWord
   		* You can use printArray method to test the output
   		*/
      	char[] secretArr = new char[secretWord.length()];    
      	for (int i = 0; i < secretArr.length; i++)
      	{
         	secretArr[i] = secretWord.charAt(i);
      	}
      	return secretArr; 
   	}
   
   	// End of Helper Code----------------------------------
    
} //program ends




