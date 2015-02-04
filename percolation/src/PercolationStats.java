
public class PercolationStats {

	private int n;
	private int t;
	private int[] count;
	private double[] scaledCount;
	private int total;
	
	public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
	{		
		if ( N <= 0 || T <= 0 ) 
		{
			throw new java.lang.IllegalArgumentException();
		}
		
		n = N;
		t = 0;
		total = T;
		count = new int[total];
		scaledCount = new double[total];
		while( t < T )
		{			
			count[t] = 0; 
			Percolation p = new Percolation(n);
			while (!p.percolates())			
			{	
				int i = StdRandom.uniform(n+1);
				int j = StdRandom.uniform(n+1);
				if (!p.isOpen(i,j))			
				{
					count[t] += 1;
					p.open(i, j);
				}				
			}
			scaledCount[t] = count[t]/(double)(n*n);
			t++;
		}
	}
	public double mean()                      // sample mean of percolation threshold
	{
		return StdStats.mean(scaledCount);
	}
	
	public double stddev()                    // sample standard deviation of percolation threshold
	{	
		return StdStats.stddev(scaledCount);
	}
	
	public double confidenceLo()              // low  endpoint of 95% confidence interval
	{		
		return mean() - (1.96 * this.stddev()) / java.lang.Math.sqrt(total);		
	}
	public double confidenceHi()              // high endpoint of 95% confidence interval
	{
		return mean() + (1.96 * this.stddev()) / java.lang.Math.sqrt(total);
	}

	   
	public static void main(String[] args)   // test client (optional)*/
	{
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		//PercolationStats ps = new PercolationStats(200,100);		
		//PercolationStats ps = new PercolationStats(2,10000);

		System.out.println(ps.mean());
		System.out.println(ps.stddev());		
		System.out.println(ps.confidenceHi());
		System.out.println(ps.confidenceLo());
		
	}
	
}
