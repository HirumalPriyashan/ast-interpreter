package CSEMachine.Symbols;

public class Symbol {
    private String token;

    public Symbol(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return  getToken();
    }

}
