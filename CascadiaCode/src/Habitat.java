/* Group 19
 * Group name: Front row
 * Timi Salam- 21392803(Timisalam)
 * Patrick Kelly-21204063(Patkelly17)
 * Sergio Jimenez- 21710801(Fletcher53&&The-Scrum-Master)
 */
public enum Habitat{
        RIVER('R', HabitatColour.BLUE),
        FOREST('F',HabitatColour.GREEN),
        MOUNTAIN('M',HabitatColour.GREY),
        WETLANDS('W',HabitatColour.BROWN),
        PRAIRIE('P',HabitatColour.YELLOW);

        private HabitatColour colour;
        private char symbol;
        Habitat(char symbol, HabitatColour colour){
                this.symbol = symbol;
                this.colour = colour;
        }
        public static char habitatColour(Habitat token){
                switch (token){
                        case RIVER -> {
                                return RIVER.symbol;
                        }
                        case FOREST -> {
                                return FOREST.symbol;
                        }
                        case MOUNTAIN -> {
                                return MOUNTAIN.symbol;
                        }
                        case WETLANDS-> {
                                return WETLANDS.symbol;
                        }
                        case PRAIRIE -> {
                                return PRAIRIE.symbol;
                        }
                        default -> throw new IllegalArgumentException("Invalid Enum placed into converter");
                }
        }

        @Override
        public String toString()
        {
                return "Habitat{" +
                        "colour=" + colour +
                        ", name='" + symbol + '\'' +
                        '}';
        }
        public char getSymbol()
        {
                return symbol;
        }


}


