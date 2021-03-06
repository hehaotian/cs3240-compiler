import java.util.HashSet;
import java.util.Set;


public class Variable extends RuleItem {
	private Grammar grammar;
	private String label;
	
	// First set
	private Set<Terminal> first = new HashSet<Terminal>();
	// Follow set
	private Set<Terminal> follow = new HashSet<Terminal>();

	// Constructors
	public Variable(Grammar grammar, String label) {
		super();
		this.grammar = grammar;
		this.label = label;
	}
	
//	public Variable(String label) {
//		super();
//		this.label = label;
//	}

	// Getters/setters
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Grammar getGrammar() {
		return grammar;
	}

	public Set<Terminal> getFirst() {
		return first;
	}

	public Set<Terminal> getFollow() {
		return follow;
	}
	
	// First/follow sets
	public boolean addToFirst(Terminal klass) {
		return first.add(klass);
	}
	
	public boolean addAllToFirst(Set<Terminal> klasses) {
		return first.addAll(klasses);
	}
	
	public boolean addToFollow(Terminal klass) {
		return follow.add(klass);
	}
	
	public boolean addAllToFollow(Set<Terminal> klasses) {
		return follow.addAll(klasses);
	}
	
	// Utility
	public boolean isVariable() {
		return true;
	}
	
	public String toString() {
		return "<"+label+">";
	}
	
	public int hashCode() {
		return label.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Variable))
			return false;

		Variable var = (Variable) obj;
		return this.label.equals(var.label);
	}
}
