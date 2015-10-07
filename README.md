# Goldbach-s-Conjecture
Verifying Goldbach's Conjecture, both sequentially and in parallel using Parallel Java2 library.

Note: In order to run these programs you need to have the Parallel Java 2 library installed.

For the sequential program do the the following:
java pj2 GoldbachSeq <lowerBound> <upperBound>

For the parallel program do the the following:
java pj2 cores=<K> GoldbachSmp <lowerBound> <upperBound>

lowerBound: is the lower bound integer to be examined
upperBound: is the upperBound integer to be examined.
K: is the number of cores you want the program to utilize for the computation.

Note: lower bound and upper bound can be BigIntegers
      You can add debug=makespan as one of the arguments for pj2 to check the running time of each.
      
      For eg:
      java pj2 debug=makespan GoldbachSeq <lowerBound> <upperBound>
