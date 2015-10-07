/*
 * File: GoldbachVbl.java
 * Package: ---
 * @author MansiNahar
 * @version 1.1
 * 
 * This class is implementation of the Vbl interface that helps to reduce the 
 * Goldbach's objects received from various threads.
 */
import java.math.BigInteger;
import edu.rit.pj2.Vbl;

public class GoldbachVbl implements Vbl{

	public BigInteger p, q;

	/*
	 * Initializes the class's Goldbach object with the one
	 * passed in the constructor.
	 */
	public GoldbachVbl() {
		p = BigInteger.valueOf(0);
		q = BigInteger.valueOf(0);
	}

	/*
	 * Copies the values of one GoldbachVbl object into this one.
	 * @param GoldbachVbl
	 * 			The Goldbach object whose values need to be copied into this object.
	 * @return GoldbachVbl
	 * 			Returns this object with new values
	 */
	public GoldbachVbl copy(GoldbachVbl GoldbachObj) {
		this.p = GoldbachObj.p;
		this.q = GoldbachObj.q;
		return this;
	}
	
	/*
	 * Creates a clone of this object
	 * @return Object
	 * 		Returns the clone of this object
	 */
	public Object clone()
	{
		try {
			GoldbachVbl golbachVbl = (GoldbachVbl) super.clone();
			golbachVbl.copy(this);
			return golbachVbl;
		}
		catch (CloneNotSupportedException exc)
		{
			throw new RuntimeException ("Shouldn't happen", exc);
		}
	}

	@Override
	/*
	 * This method sets the calling object to the one that is passed to it by calling
	 * copy on the calling object.
	 * @param Vbl
	 * 		Sets this object to the vbl object.
	 * 
	 */
	public void set(Vbl vbl) {
		this.copy (((GoldbachVbl)vbl));
	}

	@Override
	/*
	 * This method performs reduction by comparing the values of this object
	 * with the values of the passed object.
	 * @param Vbl
	 * 			The object whose values need to be compared with this object's
	 * values in order to do reduction
	 */
	public void reduce(Vbl vbl) {
		this.keepMax (((GoldbachVbl)vbl));
	}

	/*
	 * This method compares this object with the passed GoldbachVbl's object
	 * and keeps the one with greater values.
	 * 
	 * @param GoldbachVbl
	 * 		The object with whom this object's value will be compared.
	 */
	public void keepMax (GoldbachVbl goldbachObj) {

		if(this.p.compareTo(goldbachObj.p) < 0) {

			if(this.p == goldbachObj.p) {
				this.q = (this.q.compareTo(goldbachObj.q) < 0) ? goldbachObj.q : this.q;
			}
			else {
				this.p = goldbachObj.p;
				this.q = goldbachObj.q;
			}
		}
	}
	
	
	/*
	 * Setter for p value
	 * @param BigInteger
	 * 		Value of p
	 */
	public void setP (BigInteger p) {
		this.p = p;
	}
	
	/*
	 * Setter for q value
	 * @param BigInteger
	 * 		Value of q
	 */
	public void setQ (BigInteger q) {
		this.q = q;
	}
	
	/*
	 * Getter for p value of the Goldbach object
	 * @return BigInteger
	 * 		Return the value of p
	 */
	public BigInteger getP() {
		return p;
	}

	/*
	 * Getter for q value of the Goldbach object
	 * * @return BigInteger
	 * 		Return the value of q
	 */
	public BigInteger getQ() {
		return q;
	}
	
	
}
