package thomsonnfa;

class Edge {

    public char label;
    public State source;
    public State target;

    
    Edge(State source, State target, char label) {
        this.source = source;
        this.target = target;
        this.label = label;
    }

    /**
     * Return the source of this edge.
     */
    public State getSrc() {
        return source;
    }

    /**
     * Return the target of this edge.
     */
    public State getTrgt() {
        return target;
    }

    /**
     * Return the label.
     */
    public char getLabel() {
        return label;
    }

}
