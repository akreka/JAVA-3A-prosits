package tn.esprit.gestionzoo.entities;

/**
 * A zoo holding up to {@code nbrCages} animals (one animal per cage) plus a
 * separate, smaller collection of aquatic animals (pools).
 *
 * <p>Business rules enforced here: maximum capacity and uniqueness
 * (based on {@code equals}).</p>
 */
public class Zoo {

    /** Maximum number of aquatic animals the zoo can hold. */
    private static final int MAX_AQUATIC = 10;

    /** Number of cages: a constant, it can never change once the zoo is built. */
    private final int nbrCages = 25;

    private Animal[] animals;
    private int animalCount;

    private Aquatic[] aquaticAnimals;
    private int aquaticCount;

    private String name;
    private String city;

    public Zoo(String name, String city) {
        setName(name);
        setCity(city);
        this.animals = new Animal[nbrCages];
        this.aquaticAnimals = new Aquatic[MAX_AQUATIC];
    }

    // ------------------------------------------------------------------
    // Getters / setters (with validation)
    // ------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Zoo name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Zoo city cannot be empty.");
        }
        this.city = city.trim();
    }

    public int getNbrCages() {
        return nbrCages;
    }

    public int getAnimalCount() {
        return animalCount;
    }

    public int getAquaticCount() {
        return aquaticCount;
    }

    // ------------------------------------------------------------------
    // Animals management (Prosit 3)
    // ------------------------------------------------------------------

    /** Prints the zoo's name, city and number of cages. */
    public void displayZoo() {
        System.out.println("Zoo: " + name + " | City: " + city + " | Cages: " + nbrCages);
    }

    /** @return true when every cage is occupied. */
    public boolean isZooFull() {
        return animalCount >= nbrCages;
    }

    /**
     * Adds an animal if the zoo is not full and the animal is not already present.
     *
     * @return true if added, false if null, duplicate or zoo full
     */
    public boolean addAnimal(Animal animal) {
        if (animal == null || isZooFull() || searchAnimal(animal) != -1) {
            return false;
        }
        animals[animalCount++] = animal;
        return true;
    }

    /**
     * Searches an animal using {@code equals}.
     *
     * @return its index, or -1 if not found
     */
    public int searchAnimal(Animal animal) {
        if (animal == null) return -1;
        for (int i = 0; i < animalCount; i++) {
            if (animals[i].equals(animal)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Searches an animal by name (case-insensitive).
     *
     * @return its index, or -1 if not found
     */
    public int searchAnimalByName(String animalName) {
        if (animalName == null) return -1;
        for (int i = 0; i < animalCount; i++) {
            if (animals[i].getName().equalsIgnoreCase(animalName.trim())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes an animal and shifts the following ones left to close the gap.
     *
     * @return true if removed, false if not found
     */
    public boolean removeAnimal(Animal animal) {
        int index = searchAnimal(animal);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < animalCount - 1; i++) {
            animals[i] = animals[i + 1];
        }
        animals[--animalCount] = null; // free the last slot
        return true;
    }

    /** Prints every animal currently in the zoo. */
    public void displayAnimals() {
        System.out.println("Animals in " + name + " (" + animalCount + "/" + nbrCages + "):");
        if (animalCount == 0) {
            System.out.println("  (no animals)");
            return;
        }
        for (int i = 0; i < animalCount; i++) {
            System.out.println("  [" + i + "] " + animals[i]);
        }
    }

    /**
     * Compares two zoos by number of animals.
     *
     * @return the zoo with more animals (z1 on a tie)
     */
    public static Zoo comparerZoo(Zoo z1, Zoo z2) {
        if (z1 == null) return z2;
        if (z2 == null) return z1;
        return z2.animalCount > z1.animalCount ? z2 : z1;
    }

    // ------------------------------------------------------------------
    // Aquatic animals (Prosits 5 & 6)
    // ------------------------------------------------------------------

    /**
     * Adds an aquatic animal (max 10, no duplicates according to Aquatic.equals).
     *
     * @return true if added, false otherwise
     */
    public boolean addAquaticAnimal(Aquatic aquatic) {
        if (aquatic == null || aquaticCount >= MAX_AQUATIC) {
            return false;
        }
        for (int i = 0; i < aquaticCount; i++) {
            if (aquaticAnimals[i].equals(aquatic)) {
                return false;
            }
        }
        aquaticAnimals[aquaticCount++] = aquatic;
        return true;
    }

    /** Makes every aquatic animal swim: the right swim() is chosen at runtime. */
    public void makeAquaticSwim() {
        for (int i = 0; i < aquaticCount; i++) {
            aquaticAnimals[i].swim();
        }
    }

    /**
     * @return the greatest swimming depth among the penguins,
     *         or 0 if there is no penguin
     */
    public float maxSwimmingDepth() {
        float max = 0f;
        for (int i = 0; i < aquaticCount; i++) {
            if (aquaticAnimals[i] instanceof Penguin) {
                float depth = ((Penguin) aquaticAnimals[i]).getSwimmingDepth();
                if (depth > max) {
                    max = depth;
                }
            }
        }
        return max;
    }

    /** Counts and prints the number of dolphins versus penguins. */
    public void displayNumberOfAquaticByType() {
        int dolphins = 0;
        int penguins = 0;
        for (int i = 0; i < aquaticCount; i++) {
            if (aquaticAnimals[i] instanceof Dolphin) {
                dolphins++;
            } else if (aquaticAnimals[i] instanceof Penguin) {
                penguins++;
            }
        }
        System.out.println("Dolphins: " + dolphins + " | Penguins: " + penguins);
    }

    @Override
    public String toString() {
        return "Zoo{name='" + name + "', city='" + city + "', nbrCages=" + nbrCages
                + ", animals=" + animalCount + ", aquaticAnimals=" + aquaticCount + "}";
    }
}
