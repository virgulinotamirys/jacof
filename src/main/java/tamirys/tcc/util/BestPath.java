package tamirys.tcc.util;

import java.util.ArrayList;
import java.util.List;

import tamirys.tcc.problem.ppp.PathPlanningProblem;
import thiagodnf.jacof.util.random.JMetalRandom;

public class BestPath 
{
	protected JMetalRandom rand = JMetalRandom.getInstance();
	
	public int[] solve(PathPlanningProblem p)
	{
		List<Integer> pointsToVisit = new ArrayList<Integer>();
		List<Integer> solution = new ArrayList<Integer>();
		
		int currentPoint = p.getStartPoint().intValue();
		
		int targetPoint = p.getTargetPoint().intValue();
		
		for (int i = 0; i < p.getNumberOfNodes(); i++) 
		{
			if (i != currentPoint) 
			{
				pointsToVisit.add(new Integer(i));
			}
		}
		
		solution.add(new Integer(currentPoint));
		
		while (!pointsToVisit.isEmpty()) {

			int nextPoint = -1;

			double[] distanceStart = new double[8];
			distanceStart[0] = Double.MAX_VALUE;
			
			int[] positionStart = new int[8];
			positionStart[0] = -1;
			
//			for(Integer j : pointsToVisit)
//			{
//				double distance = p.getDistance(currentPoint, j);
//				if(distance < distanceStart[0])
//				{
//					distanceStart[7] = distanceStart[6];
//					positionStart[7] = positionStart[6];
//					distanceStart[6] = distanceStart[5];
//					positionStart[6] = positionStart[5];
//					distanceStart[5] = distanceStart[4];
//					positionStart[5] = positionStart[4];
//					distanceStart[4] = distanceStart[3];
//					positionStart[4] = positionStart[3];
//					distanceStart[3] = distanceStart[2];
//					positionStart[3] = positionStart[2];
//					distanceStart[2] = distanceStart[1];
//					positionStart[2] = positionStart[1];
//					distanceStart[1] = distanceStart[0];
//					positionStart[1] = positionStart[0];
//					distanceStart[0] = distance;
//					positionStart[0] = j;
//				}
//			}
			
			double distanceTarget = Double.MAX_VALUE;
			
//			for(Integer j : positionStart)
//			{
//				double distance = p.getDistance(targetPoint, j);
//				if(distance <= distanceTarget)
//				{
//					distanceTarget = distance;
//					nextPoint = j;
//				}
//			}

			solution.add(new Integer(nextPoint));
			pointsToVisit.remove(new Integer(nextPoint));
			currentPoint = nextPoint;
			if(currentPoint == targetPoint)
				break;
		}

		return solution.stream().mapToInt(x -> x).toArray();
	}
}
