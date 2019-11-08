package readNumbersFromFileToArray;

import java.io. File;
import java.io. FileNotFoundException; 
import java.util.Scanner;


public class readNumbersFromFileToArray {

    private static Scanner scan;
	private static Scanner scan2;

	public static void main (String args[]) throws FileNotFoundException 
    {
        scan = new Scanner(new File("/home/amran/test.txt"));
        int counter =0; //keep track of how many integers in the file
        while(scan.hasNextInt()) 
        {
            counter++;
            scan.nextInt();
        }
        scan2 = new Scanner(new File("/home/amran/test.txt")); 
        int array[] = new int[counter];
        for(int i=0;i<counter;i++)
        {
            array[i]=scan2.nextInt(); //fill the array with the integers
            System.out.println("array " + i + " - " + array[i]);
        } 
    }
}