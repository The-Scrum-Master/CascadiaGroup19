public enum Habitat{
        RIVER("river", HabitatColour.BLUE),
        FOREST("forest",HabitatColour.GREEN),
        MOUNTAIN("mountain",HabitatColour.GREY),
        WETLANDS("wetlands",HabitatColour.BROWN),
        PRAIRIE("prairie",HabitatColour.YELLOW);

        private HabitatColour colour;
        private String name;
        Habitat(String name, HabitatColour colour){
                this.name = name;
                this.colour = colour;
        }

        @Override
        public String toString() {
                return "Habitat{" +
                        "colour=" + colour +
                        ", name='" + name + '\'' +
                        '}';
        }
}


