



 

public class Percolation {
	
	private int map[][];
	private int n;
    private int id[];
    private int sz[];    	
	
	private int xyToi(int x, int y)
	{
		if (x <= 0 || x > n) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (y <= 0 || y > n) throw new IndexOutOfBoundsException("row index i out of bounds");
		
		return (n-1)*x + (y-1);		 
	}
	
	public Percolation(int N)               // create N-by-N grid, with all sites blocked
	{
		if ( N <= 0 )
		{
			throw new java.lang.IllegalArgumentException();
		}
//		n = N;
//		map = new int[n][n];
//		for (int x = 0; x < n; x++) {
//			for (int y = 0; y < n; y++) {
//				map[x][y] = 0;
//			}
//		}
		
		n = N;
        id = new int[N*N];
        sz = new int[N*N];
        for (int i = 0; i < N*N+1; i++) {
            id[i] = i;
            sz[i] = 1;
        }
		//WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N*N);
	}

	public void open(int i, int j)          // open site (row i, column j) if it is not open already
	{		
		
		if ( i == 0 || ( j > 0 && map[i][j-1] == 2 ) || ( j+1 < n && map[i][j+1] == 2 )
				|| ( i > 0 && map[i-1][j] == 2 ) || ( i+1 < n && map[i+1][j] == 2 ) )			
		{
			map[i][j] = 2;						
			if ( j > 0 && map[i][j-1] == 1 ) 
			{
				open(i,j-1);
			}
			if ( j+1 < n && map[i][j+1] == 1 ) 
			{
				open(i,j+1);
			}
			if ( i > 0 && map[i-1][j] == 1 )
			{
				open(i-1,j);
			}
			if ( i+1 < n && map[i+1][j] == 1 ) 
			{
				open(i+1,j);
			}
		}
		else 
			map[i][j] = 1;
	}
	
	public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	{
//		if ( map[i][j] == 0 )
//			return false;
//		else
//			return true;	
		
		int p = xyToi(i,j);
		int r = p;
		
		while (r != id[r])
            r = id[r];
		if ( p == r && sz[r] == 1 )
			return false;
		else 
			return true;
        //return p;
        
	}
	
	public boolean isFull(int i, int j)     // is site (row i, column j) full?
	{
		if ( map[i][j] == 2 )
			return true;
		else
			return false;	
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
		boolean result = false;
		for (int y = 0; y < n; y++) {
			if (isFull(n-1,y))
			{
				result = true;
				break;
			}
		}		
		return result;
	}

	/*
	public void printMap()
	{
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++){
				System.out.print(map[x][y] + " ");										
			}
			System.out.println();
		}
		
		System.out.println();
	}
	*/
}
