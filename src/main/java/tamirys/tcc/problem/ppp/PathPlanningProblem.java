package tamirys.tcc.problem.ppp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tamirys.tcc.util.BestPath;
import tamirys.tcc.util.io.PPPLIBReader;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.io.InstanceReader;

public class PathPlanningProblem extends Problem
{
	public double Q = 1.0;
	
	protected double[][] coord;
	
	/** Number of Points */
	protected int numberOfPoints;
	
	/** Best Path heuristic */
	protected double cbp;
	
	/** Initial Position */
	protected Integer startPoint;
	
	/** Target Position */
	protected Integer targetPoint;
	
	public PathPlanningProblem(String filename) throws IOException 
	{
		this(filename, false);
	}
	
	public PathPlanningProblem(String filename,boolean isTspLibFormmat) throws IOException 
	{
		PPPLIBReader r = new PPPLIBReader(new InstanceReader(new File(filename)));
		
		numberOfPoints = r.getDimension();
		
		coord = r.getCoord();
		
		startPoint = new Integer(r.getStartPosition());
		
		targetPoint = new Integer(r.getTargetPosition());
				
		BestPath bp = new BestPath();		
		
		this.cbp = evaluate(bp.solve(this));

		System.out.println("Best Solution: " + Arrays.toString(getTheBestSolution()));
		System.out.println("Best Value: " + evaluate(getTheBestSolution()));
	}
	
	public Integer getStartPoint()
	{
		return this.startPoint;
	}
	
	public Integer getTargetPoint()
	{
		return this.targetPoint;
	}
	
//	public double getDistance(int i, int j) 
//	{
//		if(i < 0 || j < 0)
//			return Double.MAX_VALUE;
//		return this.distance[i][j];
//	}
	
	// Mudar
	public int[] getTheBestSolution()
	{
		return new int[]{0};
	}

	@Override
	public boolean better(double s1, double best) 
	{
		// TODO Auto-generated method stub
		return s1 < best;
	}

	@Override
	public double evaluate(int[] solution) 
	{
		double totalDistance = 0;

		for (int h = 1; h < solution.length; h++) {
			
			int i = solution[h - 1];
			int j = solution[h];
			
//			totalDistance += distance[i][j];
		}
		
		return totalDistance;
	}

	@Override
	public int getNumberOfNodes() 
	{
		// TODO Auto-generated method stub
		return numberOfPoints;
	}

	@Override
	public double getCnn() 
	{
		// TODO Auto-generated method stub
		return cbp;
	}

	@Override
	public double getDeltaTau(double tourLength, int i, int j) 
	{
		// TODO Auto-generated method stub
		return Q / tourLength;
	}

	@Override
	public double getNij(int i, int j) 
	{
		// TODO Auto-generated method stub
//		return 1.0 / distance[i][j];
		return 0;
	}

	@Override
	public String toString() 
	{
		// TODO Auto-generated method stub
		return PathPlanningProblem.class.getSimpleName();
	}

	@Override
	public List<Integer> initNodesToVisit(int startingNode) 
	{
		List<Integer> nodesToVisit = new ArrayList<>();
		for (int i = 0; i < getNumberOfNodes(); i++) {
			if (i != startingNode) {
				nodesToVisit.add(new Integer(i));
			}
		}

		return nodesToVisit;
	}

	@Override
	public List<Integer> updateNodesToVisit(List<Integer> tour, List<Integer> nodesToVisit) 
	{
		if (nodesToVisit.isEmpty()) {
			if (!tour.get(0).equals(tour.get(tour.size() - 1))) {
				nodesToVisit.add(tour.get(0));
			}
		}
		
		return nodesToVisit;
	}

}
