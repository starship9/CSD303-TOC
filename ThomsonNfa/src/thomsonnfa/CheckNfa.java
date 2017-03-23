package thomsonnfa;

class CheckNfa {

    public State InState;
    public State CurrState;

    /**
     * Constructor.
     *
     * Creates an empty NFA by instantiating a start state.
     */
    public CheckNfa() {

        InState = new State();
        CurrState = InState;
    }

    /**
     * Returns initialState.
     */
    public State getInState() {
        return InState;
    }

    /**
     * Returns currentState.
     */
    public State getCurrState() {
        return CurrState;
    }

    /**
     * Removes the initial state. Used when connecting a capturing group to the
     * rest of the automaton.
     */
    public void removeInitial() {
        InState = null;
    }

    
    public void concat(char x) {

        State nextState = new State();
        Edge e = new Edge(CurrState, nextState, x);

        CurrState.addOutLink(e);
        nextState.addInLink(e);

        nextState.makeAccept();
        CurrState.notAccept();

        CurrState = nextState;

    }

    
    public State altr(State bcb) {
        State s = new State();
        Edge epsilon = new Edge(bcb, s, 'E');
        bcb.addOutLink(epsilon);
        s.addInLink(epsilon);
        CurrState = s;
        return s;
    }

    
    public void kleeneStar(char x) {

        //Simplified version of kleene star:
        //move backward one state (through x)
        CurrState = CurrState.sourceOf(x);
        CurrState.makeAccept();

        //go forward (through x). The new currentState has already set as
        //an accept state through concat().
        CurrState = CurrState.transition(x);

        Edge e = new Edge(CurrState, CurrState, x);
        CurrState.addInLink(e);
        CurrState.addOutLink(e);

    }

       

}
