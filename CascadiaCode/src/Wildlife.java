public enum Wildlife {
    ELK("E"),
    BEAR("B"),
    SALMON("S"),
    HAWK("H"),
    FOX("F");
    private String symbol;
    Wildlife(String symbol) {
        this.symbol =symbol;
    }

    @Override
    public String toString() {
        return "Wildlife{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
