import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import tamirys.tcc.problem.ppp.PathPlanningProblem;
import thiagodnf.jacof.aco.MaxMinAntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.ExecutionStats;

public class MMASMain {
	
	// Classe Logger registra componentes específicos de um sistema.
	static final Logger LOGGER = Logger.getLogger(MMASMain.class);

	public static void main(String[] args) throws ParseException, IOException 
	{

//		String instance = "src/main/resources/problems/tsp/tamirys5.tsp";
		String instance = "src/main/resources/problems/tsp/tamirys.tsp";
		
		// TSP - Dado um conjunto de cidades e distância entre cada par de cidades, 
		// o problema é encontrar o menor p Roteamento sustentável que visita cada cidade
		// exatamente uma vez e retorna ao ponto de partida. 
		Problem problem = new PathPlanningProblem(instance);
		
		MaxMinAntSystem aco = new MaxMinAntSystem(problem);
		
		// Número de formigas 
		aco.setNumberOfAnts(10);
		
		// Número de iterações
		aco.setNumberOfIterations(1);
		
		// Importância do feromônio
		aco.setAlpha(1.0);
		
		// Importância da informação heurística, no TSP, o custo entre uma cidade e outra.
		aco.setBeta(2.0);
		
		// Parâmetro que regula a taxa de evaporação (novas soluções) do feromônio.
		aco.setRho(1.0);
		
		// Situação na qual todas as formigas seguem sempre o mesmo percurso, causado
		// pelo excessivo crescimento de feromônios nas arestas de uma viagem subótima.
		aco.setStagnation(10);

		ExecutionStats es = ExecutionStats.execute(aco, problem);
		es.printStats();
	}

}
