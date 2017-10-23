import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.MaxMinAntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.problem.tsp.TravellingSalesmanProblem;
import thiagodnf.jacof.util.ExecutionStats;

public class MMASRunner {

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(MMASRunner.class);
	
	public static void main(String[] args) throws ParseException, IOException {

		String instance = "src/main/resources/problems/tsp/oliver30.tsp";

		Problem problem = new TravellingSalesmanProblem(instance);

		MaxMinAntSystem aco = new MaxMinAntSystem(problem);

		aco.setNumberOfAnts(30);
		aco.setNumberOfIterations(5000);
		aco.setAlpha(1.0);
		aco.setBeta(2.0);
		aco.setRho(0.1);
		aco.setStagnation(1000);

		ExecutionStats es = ExecutionStats.execute(aco, problem);
		es.printStats();
	}

}
