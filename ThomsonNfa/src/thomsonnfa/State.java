package thomsonnfa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class State {

    public boolean acceptState = false; //whether or not this is an accept state
    public List<Edge> outLinks = new ArrayList<>();
    public List<Edge> inLinks = new ArrayList<>();

    
    public boolean isAccept() {
        return acceptState;
    }

    /**
     * Set this as an accept state.
     */
    public void makeAccept() {
        this.acceptState = true;
    }

    /**
     * Set as a non-accept state.
     */
    public void notAccept() {
        this.acceptState = false;
    }

    
    public List<Edge> getOutLinks() {
        return outLinks;
    }

    
    public void addOutLink(Edge e) {
        this.outLinks.add(e);
    }

    /**
     * Returns the list of this state's incoming links (edges).
     *
     * @return the inLinks
     */
    public List<Edge> getInLinks() {
        return inLinks;
    }

    
    public void addInLink(Edge e) {
        this.inLinks.add(e);
    }

    
    public boolean containsOutLink(char x) {
        for (Edge e : outLinks) {
            if (e.getLabel() == x) {
                return true;
            }
        }
        return false;
    }

    
    public State transition(char x) {

        State ret = new State();

        for (Edge e : outLinks) {
            if (e.getLabel() == x) {
                ret = e.getTrgt();
            }
        }
        return ret;
    }

    
    public State sourceOf(char x) {

        State ret = new State();

        for (Edge e : inLinks) {
            if (e.getLabel() == x) {
                ret = e.getSrc();
            }
        }
        return ret;
    }

    
    public void removeOutLink(char x) {

        Iterator<Edge> itr = outLinks.iterator();
        while (itr.hasNext()) {
            Edge e = itr.next();
            if (e.getLabel() == x) {
                itr.remove();
            }
        }

    }

}
