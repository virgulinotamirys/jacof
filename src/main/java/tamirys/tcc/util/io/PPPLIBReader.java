package tamirys.tcc.util.io;

import thiagodnf.jacof.util.io.InstanceReader;

public class PPPLIBReader 
{
	protected InstanceReader reader;

	private int dimension;

	protected double[][] coord;

//	private double[][] distance;
	
	private double[] startPoint;
	
	private double[] targetPoint;
			
	public PPPLIBReader(InstanceReader reader) {
		this.reader = reader;

		readHeader();
		readCoordinates();
//		convertCoordToDistance();
	}

	public void readHeader() {
		String line = reader.readLine();

		while (!line.equalsIgnoreCase("NODE_COORD_SECTION")) {
			String[] split = line.split(":");

			String key = split[0].trim();

			if (key.equalsIgnoreCase("DIMENSION")) {
				dimension = Integer.valueOf(split[1].trim());
			}
			
			if(key.equalsIgnoreCase("START_POINT"))
			{
				startPoint = new double[2];
				String[] value = split[1].trim().split(" ");
				startPoint[0] = Double.valueOf(value[0].trim());
				startPoint[1] = Double.valueOf(value[1].trim());
			}
			
			if(key.equalsIgnoreCase("TARGET_POINT"))
			{
				targetPoint = new double[2];
				String[] value = split[1].trim().split(" ");
				targetPoint[0] = Double.valueOf(value[0].trim());
				targetPoint[1] = Double.valueOf(value[1].trim());
			}

			line = reader.readLine();

			if (line == null) {
				break;
			}
		}
	}

	private void readCoordinates() {
		coord = new double[dimension][3];

		String line = reader.readLine();
		int i = 0;
		while (line != null) {
			String[] split = line.split(" ");

			coord[i][0] = Double.valueOf(split[0].trim());
			coord[i][1] = Double.valueOf(split[1].trim());
			coord[i][2] = Double.valueOf(split[2].trim());

			i++;
			line = reader.readLine();
		}
	}

//	private void convertCoordToDistance() {
//		distance = new double[dimension][dimension];
//
//		for (int i = 0; i < dimension; i++) {
//			for (int j = i; j < dimension; j++) {
//				if (i != j) {
//					double x1 = coord[i][1];
//					double y1 = coord[i][2];
//					double x2 = coord[j][1];
//					double y2 = coord[j][2];
//
//					distance[i][j] = (x2 + y2) - (x1 + y1);
//					if(distance[i][j] < 0)
//						distance[i][j] = distance[i][j] * -1; 
//					distance[j][i] = distance[i][j];
//				}
//			}
//		}
//	}

//	public static double euclideanDistance(double x1, double y1, double x2, double y2) {
//		double xDistance = Math.abs(x1 - x2);
//		double yDistance = Math.abs(y1 - y2);
//
//		return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
//	}
	
	public int getStartPosition()
	{
		int point = 0;
		Double menorDiferencaX = null;
		Double menorDiferencaY = null;
		for(int i = 0; i < dimension; i++)
		{
			double difX = startPoint[0] - coord[i][1];
			double difY = startPoint[1] - coord[i][2];
			if(difX < 0)
				difX = difX * -1;
			if(difY < 0)
				difY = difY * -1;
			if(menorDiferencaX == null && menorDiferencaY == null)
			{
				point = i;
				menorDiferencaX = difX;
				menorDiferencaY = difY;
			} else if(difX <= menorDiferencaX && difY <= menorDiferencaY)
			{
				point = i;
				menorDiferencaX = difX;
				menorDiferencaY = difY;
			}
		}
		return point;
	}
	
	public int getTargetPosition()
	{
		int point = 0;
		Double menorDiferencaX = null;
		Double menorDiferencaY = null;
		for(int i = 0; i < dimension; i++)
		{
			double difX = targetPoint[0] - coord[i][1];
			double difY = targetPoint[1] - coord[i][2];
			if(difX < 0)
				difX = difX * -1;
			if(difY < 0)
				difY = difY * -1;
			if(menorDiferencaX == null && menorDiferencaY == null)
			{
				point = i;
				menorDiferencaX = difX;
				menorDiferencaY = difY;
			} else if(difX <= menorDiferencaX && difY <= menorDiferencaY)
			{
				point = i;
				menorDiferencaX = difX;
				menorDiferencaY = difY;
			}
		}
		return point;
	}

	public int getDimension() {
		return dimension;
	}
	
	public double[][] getCoord()
	{
		return coord;
	}

//	public double[][] getDistance() {
//		return distance;
//	}
}
