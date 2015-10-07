/*
 * File: GoldbachSmp.java
 * Package: ---
 * @author MansiNahar
 * @version 1.2
 * 
 * This program verifies Goldbach's Conjecture for all even integers in a given range
 * by utilizing the power of parallel computing.
 */
import java.math.BigInteger;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

/*
 * This class verifies the Goldbach's conjecture by utilizing the advantage of
 * parallel computing. It runs a parallelFor loop for every integer, verifying the
 * Golbach's conjecture for that integer.
 */
public class GoldbachSmp extends Task {

	BigInteger lowerBound, upperBound, evenNumber;
	GoldbachVbl goldbachVbl;

	/*
	 * Main program
	 * @param args Lower bound and upper bound of the range of numbers
	 * 			that need to be used to verify Goldbach's conjecture.
	 * @exception Exception
	 * 			Throws general exception, if occurred
	 */
	public void main(String[] args) throws Exception {
		
		// If number of arguments not equal to 2 then print usage.
		if(args.length != 2) {
			System.out.println("Wrong number of arguments passed.");
			usage();
		}
		
		// Check if the arguments passed to the program are valid
		if(!sanitizeInput(args[0], args[1])) return;
		
		
		goldbachVbl = new GoldbachVbl();
		evenNumber = BigInteger.valueOf(0);
		int N = upperBound.subtract(lowerBound).intValue();

		// Parallel for loop that takes in one even number at a time and
		// finds the p(prime) and q(prime) values that add up to that number
		parallelFor(0, N/2).exec(new Loop()
		{   
			GoldbachVbl thrGoldbachVbl;
			GoldbachVbl golbachVblObj;
			BigInteger evenNumber;
			
			/*
			 * Initializes the thread local variable before the thread runs
			 */
			public void start() {
				thrGoldbachVbl = threadLocal (goldbachVbl);
				golbachVblObj = new GoldbachVbl();
			}

			@Override
			/*
			 * Computes the value of p and q for a given evenNumber
			 * @param i
			 * 			Index of the parallel for loop
			 */
			public void run(int i) throws Exception {
			
				evenNumber = lowerBound.add(BigInteger.valueOf(i*2));

				golbachVblObj.setP(BigInteger.valueOf(2));
				golbachVblObj.setQ(evenNumber.subtract(golbachVblObj.getP()));

				if(!(golbachVblObj.getQ().isProbablePrime(100))) {
					while(true) {
						golbachVblObj.setP(golbachVblObj.getP().nextProbablePrime());
						golbachVblObj.setQ(evenNumber.subtract(golbachVblObj.getP()));
						if(golbachVblObj.getQ().isProbablePrime(100)) {
							break;
						}	
					}
				}

				// If the p and q values that this thread already has are smaller
				// than the newly computed ones then replace them with the new values.
				thrGoldbachVbl.keepMax(golbachVblObj);
			}});

		System.out.println((goldbachVbl.getP().add(goldbachVbl.getQ())) + " = " + goldbachVbl.getP() + " + " + goldbachVbl.getQ());
	}

	/*
	 * This function prints the usage of this program
	 */
	private static void usage()
	{
		System.out.println("Usage: java pj2 GoldBachSmp <lowerBound> <upperBound>");
		System.out.println("lowerBound and upperBound should be numbers.");
	}
	
	/*
	 * Checks if provided arguments are proper or not.
	 * If they are erroneous then print error message
	 * @param String
	 * 		This is the lower bound in string format
	 * @param String
	 * 		This is the upper bound in string format
	 * @return boolean
	 * 		True if input is not erroneous, false otherwise
	 */
	private boolean sanitizeInput(String lb, String ub) 
	{
		try {
			lowerBound = new BigInteger(lb);
			upperBound = new BigInteger(ub);
			
			// Checking if the lowerbound is greater than the upper bound
			if(upperBound.compareTo(lowerBound) < 0) {
				System.out.println("Upper bound needs to be greater than lower bound");
				System.out.println("See usage below for more info: ");
				usage();
				return false;
			}
			
			if(lowerBound.compareTo(BigInteger.valueOf(2)) < 0) {
				System.out.println("Goldbach's Conjecture is valid for even numbers greater than 2.");
				System.out.println("So please enter lower bound greater than 2");
				System.out.println("See usage below for more info: ");
				usage();
				return false;
			}
			
			//Checking if lower bound is an even integer
			if(lowerBound.mod(BigInteger.valueOf(2)) != BigInteger.ZERO) {

				System.out.println("lower bound must be an even integer");
				System.out.println("See usage below for more info: ");
				usage();
				return false;
			}
			
			//Checking if upper bound is an even integer
			if(upperBound.mod(BigInteger.valueOf(2)) != BigInteger.ZERO) {

				System.out.println("upper bound must be an even integer");
				System.out.println("See usage below for more info: ");
				usage();
				return false;
			}

		}
		
		// If arguments are not numbers then throw print usage function
		catch(NumberFormatException e) {
			System.out.println("The arguments, lowerBound and upperBound should be numbers.");
			System.out.println("See usage below for more info: ");
			usage();
		}
		catch(Exception e) {
			System.out.println("Exception occurred: ");
			System.out.println("See usage below for more info: ");
			usage();
			e.printStackTrace();
		}
		
		return true;
	}
}
