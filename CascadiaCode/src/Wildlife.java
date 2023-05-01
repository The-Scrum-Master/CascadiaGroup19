/* Group 19
 * Group name: Front row
 * Timi Salam- 2139203(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public enum Wildlife {
    ELK("E"),
    BEAR("B"),
    SALMON("S"),
    HAWK("H"),
    FOX("F");
    private final String symbol;
    public static Wildlife randWildlife(){
        switch (Tile.randomNumberGenerator(5)){
            //generates and returns 1 of 5 tokens
            case 0:
                return Wildlife.HAWK;
            case 1:
                return Wildlife.SALMON;
            case 2:
                return Wildlife.ELK;
            case 3:
                return Wildlife.BEAR;
            case 4:
                return Wildlife.FOX;
            default:
                throw new IllegalArgumentException("random num generator limit error");
        }
    }

    public static String animalSymbol(Wildlife token){
        switch (token){
            case HAWK -> {
                return HAWK.symbol;
            }
            case ELK -> {
                return ELK.symbol;
            }
            case BEAR -> {
                return BEAR.symbol;
            }
            case FOX -> {
                return FOX.symbol;
            }
            case SALMON -> {
                return SALMON.symbol;
            }
            default -> throw new IllegalArgumentException("Invalid Enum placed into converter");
        }
    }

    Wildlife(String symbol) {
        this.symbol = symbol;
    }
}
