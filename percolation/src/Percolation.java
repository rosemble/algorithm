



 

public class Percolation {
	
	private int n;
    private int id[];
    private int sz[];   	
	private WeightedQuickUnionUF wuf;
	
	private int xyToi(int x, int y)
	{		
		if (x <= 0 || x > n) throw new IndexOutOfBoundsException("row index i out of bounds:" + x);
		if (y <= 0 || y > n) throw new IndexOutOfBoundsException("row index i out of bounds" + y);

		return n*(x-1) + (y-1);		 
	}
	
	public Percolation(int N)               // create N-by-N grid, with all sites blocked
	{
		if ( N <= 0 )
		{
			throw new java.lang.IllegalArgumentException();
		}
		
		n = N;		
        id = new int[N*N];
        sz = new int[N*N+2];
        wuf = new WeightedQuickUnionUF(n*n+2);
        for (int i = 0; i < N*N; i++) {
        	//id[i] = 0;        	
        	sz[i] = 0;
        }
        
        sz[n*n] = 1;
        sz[n*n+1] = 1;        
	}
	
	private void union(int p, int q)
	{
		if ( !wuf.connected(p, q))
		{
			wuf.union(p, q);
		}
	}

	public void open(int i, int j)          // open site (row i, column j) if it is not open already
	{	
		int p = xyToi(i, j);
		//id[p] = p;
		sz[p] = 1;
		
		
		if ( i == 1 )
		{
			union(p, n*n);
		} 
		else if ( i == n )
		{
			union(p, n*n+1);
		}

		if ( j-1 >= 1 && isOpen(i,j-1) )
			union(p, xyToi(i,j-1));
		
		if ( j+1 <= n && isOpen(i,j+1) )
			union(p, xyToi(i,j+1));
		
		if ( i-1 >= 1 && isOpen(i-1,j) )
			union(p, xyToi(i-1,j));
		
		if ( i+1 <= n && isOpen(i+1,j) )
			union(p, xyToi(i+1,j));
	}
	
	private boolean connected(int p, int q) {
		//System.out.println("connected?" + p + "(" + find(p) + ") " + q + "(" + find(q) + ")");
        return find(p) == find(q) && ( sz[find(p)] != 0 );
    }
	
//	private  void union(int p, int q) {
//        int rootP = find(p);
//        int rootQ = find(q);
//        
//        if (rootP == rootQ) return;
//
//        // make smaller root point to larger one
//        if   (sz[rootP] < sz[rootQ]) { id[rootP] = rootQ; sz[rootQ] += sz[rootP]; }
//        else                         { id[rootQ] = rootP; sz[rootP] += sz[rootQ]; }
//    }
	
	private int find(int p)
	{		
		while (p != id[p] )
            p = id[p];
        return p;
	}
	
	public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	{		
		int p = xyToi(i,j);
		
		return sz[p] == 1;		
	}
	
	public boolean isFull(int i, int j)     // is site (row i, column j) full?
	{
		
		int p = xyToi(i,j);
		
		boolean result = wuf.connected(p, n*n);
//		//System.out.println(i + "," + j);
//		boolean result = false;
//		for (int y = 1; y <= n; y++)
//		{
//			//System.out.println(p + " " + xyToi(1,y));
//			if ( isOpen(i,j) &&  connected(p, xyToi(1, y))) 
//			{				
//				System.out.println("connected" + p + "(" + find(p) + ") " + xyToi(1, y) + "(" + find(xyToi(1, y)) + ")");
//				//System.out.println(p + " " + xyToi(1,y));
//				result = true;
//				break;
//			}
//		}
		
		return result;
		
//		if ( map[i][j] == 2 )
//			return true;
//		else
//			return false;	
		// left right top down
		//System.out.println("isfull:" + String.valueOf(i)+','+String.valueOf(j) );
		
//		if ( isOpen(i,j) )
//		{
//			if ( i == 0 && isOpen(i,j))  // if map[i][j] is top row and isopen
//			{	
//				printMap();
//				return true;
//			}
//			else if ( j > 0 && isOpen(i,j-1) ) 
//			{
//				//System.out.println("1:" + String.valueOf(i)+','+String.valueOf(j) + isOpen(i,j-1) );
//				return isFull(i,j-1, 1);
//			}
//			else if ( j+1 < n && isOpen(i, j+1) && d != 1)
//			{
//				System.out.println("2:" + String.valueOf(i)+','+String.valueOf(j) + isOpen(i, j+1) + map[i][j+1] );
//				return isFull(i, j+1, 2);
//			}
//			else if ( i > 0 && isOpen(i-1, j))
//			{
//				System.out.println("3:" + String.valueOf(i)+','+String.valueOf(j) + isOpen(i-1, j) );
//				return isFull(i-1, j, 3);
//			}
//			else if ( i+1 < n && isOpen(i+1,j))
//			{
//				System.out.println("4:" + String.valueOf(i)+','+String.valueOf(j) + isOpen(i+1,j) );
//				return isFull(i+1, j, 4);
//			}
//			else 
//				return false;
//		}
//		else 
//			return false;
	}
	
	public boolean percolates()             // does the system percolate?
	{
//		boolean result = false;
//		for (int y = 1; y <= n; y++) {
//			
//			//printMap();
//			if (isFull(n,y))
//			{
//				//printMap();
//				result = true;
//				break;
//			}
//			//System.out.println("PER:" + n + "," + y );
//			
//		}
		
		boolean result = wuf.connected(n*n, n*n+1);
		return result;
	}
	
//	public void printMap()
//	{
//		for (int x = 1; x <= n; x++) {
//			for (int y = 1; y <= n; y++){
//				System.out.print(id[xyToi(x,y)] + " ");										
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//	}
//	
//	public void printlinear()
//	{
//		System.out.println(id.length);
//		for(int i = 0; i < id.length; i++)
//			System.out.print(id[i] + " ");
//	}
	
}
