import java.io.IOException;


public class TestScanner {

	public static void main(String[] args) throws IOException {
		Parser.debug = true;
		
		Parser parser = new Parser();
		parser.buildFromFile("specs/minire.txt");
		System.out.println(parser.bigDFA.toTableString(true));
		
		parser.scanAndOutput("scripts/script1.txt");
		
		TokenClass klass = parser.getTokenClass("ID");
		
		//if (klass.getNFA() != klass.getNFA().getStart().getNFA()) System.out.println("THAT'S AN ISSUE!");
		DFA dfa = klass.getDFA();
		State[][] table = dfa.toTable();
		System.out.println(dfa.toTableString(true));
		
		
		// Now try table walking!
		String input = "ab_b2fg4ij";
		System.out.println("Input: "+input);
		boolean accepts = DFA.walkTable(input, table).accepts;
		
		System.out.println('"'+input+'"'+" is"+((accepts) ? "" : " NOT")+" a valid "+klass.getName());
		
		System.out.println(parser.scanToken("112"));
	}

}
