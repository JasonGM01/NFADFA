import java.util.HashMap;

class Accepter<T> {
    private T[][] matrix;
    private HashMap<String, Integer> states;
    private HashMap<String, Integer> symbols;
    private String[] finalState;
    private String initialState;

    // constructors
    public Accepter() {
    }

    public Accepter(T[][] matrix, String[] states, String[] symbols, String initialState, String[] finalState){
        this.states = new HashMap<>();
        this.symbols = new HashMap<>();
        setMatrix(matrix);
        setFinalState(finalState);
        setInitialState(initialState);
        setStates(states);
        setSymbols(symbols);
    }

    // set and get
    private void setMatrix(T[][] matrix){this.matrix = matrix;}
    private void setFinalState(String[] finalState){this.finalState = finalState;}
    private void setInitialState(String initialState){this.initialState = initialState;}
    private void setStates(String[] states){
        for(int i = 0; i < states.length; i++){
            this.states.put(states[i], i);}
    }
    private void setSymbols(String[] symbols){
        for(int i = 0; i < symbols.length; i++){
            this.symbols.put(symbols[i], i);}
    }

    public void setTransition(String state, String symbol, T destination) {
        int stateIndex = 0;
        int symbolIndex = 0;

        if(!this.states.containsKey(state)){System.out.printf("Invalid state"); return;}
        if(!this.symbols.containsKey(symbol)){System.out.printf("Invalid symbol"); return;}
        
        stateIndex = this.states.get(state);
        symbolIndex = this.symbols.get(symbol);
        
        matrix[stateIndex][symbolIndex] = destination;
    }

    public Integer getStateIndex(String state){return this.states.get(state);}
    public Integer getSymbolIndex(String symbol){return this.symbols.get(symbol);}
    public String getInitialState(){return this.initialState;}
    public String[] getFinalState(){return this.finalState;}
    public T getTransition(String state, String symbol){
        if(!this.states.containsKey(state)) return null;
        if(!this.symbols.containsKey(symbol)) return null;
        int row = this.states.get(state);
        int col = this.symbols.get(symbol);
        return this.matrix[row][col];
    }
}