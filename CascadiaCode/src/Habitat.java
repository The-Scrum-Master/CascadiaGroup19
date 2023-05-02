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
        WETLANDS('W',HabitatColour.CYAN),
        PRAIRIE('P',HabitatColour.YELLOW);

        private HabitatColour colour;
        private char symbol;
        private String name;
        Habitat(char symbol, HabitatColour colour){
                this.symbol = symbol;
                this.colour = colour;
        }

        private String symbolToString(char ch){
                switch(ch){
                        case 'R':
                        name = "River";
                        return name;
                        case 'F':
                        name = "Forest";
                        return name;
                        case 'M':
                        name = "Mountain";
                        return name;
                        case 'P':
                        name = "Prairie";
                        return name;
                        case 'W':
                        name = "Wetland";
                        return name;
                        default: throw new IllegalArgumentException("unknown character passed to symbolToString");
                }
        }
        @Override
        public String toString() {
                return "Habitat: " + colour + " " + symbolToString(symbol);
        }

        public char getSymbol()
        {
                return symbol;
        }
}


