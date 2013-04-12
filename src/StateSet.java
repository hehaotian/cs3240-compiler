import java.util.HashSet;
import java.util.Iterator;

public class StateSet implements Cloneable {
    public HashSet<State> states = new HashSet<State>();

    /* Constructors */
    public StateSet() {

    }

    public StateSet(HashSet<State> states) {
        this.states = states;
    }

    /* Setters/Getters */
    public HashSet<State> getStates() {
        return states;
    }

    public void setStates(HashSet<State> states) {
        this.states = states;
    }

    /* Manipulation */
    public void add(State state) {
        states.add(state);
    }

    /* Info */
    public boolean contains(State state) {
        return states.contains(state);
    }

    public boolean equals(Object other) {
        if (other instanceof StateSet) {
            StateSet otherSet = (StateSet)other;
            return states.equals(otherSet.getStates());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return states.hashCode();
    }

    // Do any of the states accept?
    public boolean accepts() {
        Iterator<State> iter = states.iterator();
        while (iter.hasNext())
            if (iter.next().getAccepts()) return true;

        return false;
    }

    // What characters cause transitions in this set?
    public HashSet<Character> transitionCharacters() {
        HashSet<Character> chars = new HashSet<Character>();
        Iterator<State> iter = states.iterator();
        while (iter.hasNext()) {
            Iterator<Transition> transIter = iter.next().getTransitions().iterator();
            while (transIter.hasNext())
                chars.add(transIter.next().c);
        }

        return chars;
    }

    public HashSet<State> statesReachableOn(Character on) {
        HashSet<State> reachable = new HashSet<State>();
        Iterator<State> iter = states.iterator();
        while (iter.hasNext()) {
            State state = iter.next();
            reachable.addAll(state.getNFA().statesReachableOn(state, on));
        }

        return reachable;
    }

    public StateSet transition(Character on) {
        HashSet<State> reachable = statesReachableOn(on);

        if (reachable.equals(states)) {
            return this;
        } else {
            return new StateSet(reachable);
        }
    }

    public String toString() {
//        String string = "{";
//        //string += ""+states.size()+":";
//        String delim = ",";
//        Iterator<State> iter = states.iterator();
//        while (iter.hasNext()) {
//            string += iter.next().toString();
//            if (iter.hasNext()) string += delim;
//        }
//        string += "}";
//        return string;
    	return states.toString();
    }

    public State toState() {
        State state = new State(toString());
        state.setAccepts(accepts());

        for(State currState : states){
        	if(currState.getAccepts()){
                state.klass = currState.klass;
                System.out.println("setting state class "+state.klass);
            }
            /*if(currState.getAccepts() && state.klass == null){
                state.klass = currState.klass;
                System.out.println("setting state class "+state.klass);
            }
            else if(currState.getAccepts()){
                System.out.println("MORE THAN ONE KLASS IN A STATE SET");
            }*/
        }
        System.out.println("state's new klass variable "+state.klass);
        return state;
    }

//    @Override
//    public Object clone(){
//        try {
//            StateSet set = (StateSet) super.clone();
//            Iterator<State> stateIter = states.iterator();
//            while(stateIter.hasNext()){
//                State s = stateIter.next();
//                set.states.remove(s);
//                set.states.add((State) s.clone());
//            }
//            return set;
//        } catch (CloneNotSupportedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        }
//    }
}
