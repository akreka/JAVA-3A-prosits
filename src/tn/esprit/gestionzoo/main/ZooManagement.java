package tn.esprit.gestionzoo.main;

import tn.esprit.gestionzoo.entities.Animal;
import tn.esprit.gestionzoo.entities.Aquatic;
import tn.esprit.gestionzoo.entities.Dolphin;
import tn.esprit.gestionzoo.entities.Penguin;
import tn.esprit.gestionzoo.entities.Terrestrial;
import tn.esprit.gestionzoo.entities.Zoo;

import java.util.Scanner;

/**
 * Entry point of the Zoo Management System.
 * Each prosit is exercised in its own method, in progressive order.
 */
public class ZooManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        prosit1();
        prosit2(scanner);
        prosit3();
        prosit5And6();

        scanner.close();
    }

    // ------------------------------------------------------------------
    // Prosit 1: basic variables
    // ------------------------------------------------------------------
    private static void prosit1() {
        title("PROSIT 1 - Basic variables");
        int nbrCages = 20;
        String zooName = "my zoo";
        System.out.println(zooName + " comporte " + nbrCages + " cages");
    }

    // ------------------------------------------------------------------
    // Prosit 2: keyboard input with validation
    // ------------------------------------------------------------------
    private static void prosit2(Scanner scanner) {
        title("PROSIT 2 - Scanner input & validation");

        String zooName = readNonEmptyString(scanner, "Enter the zoo name: ");
        int nbrCages = readPositiveInt(scanner, "Enter the number of cages: ");

        System.out.println(zooName + " comporte " + nbrCages + " cages");
    }

    /** Asks until a non-empty string is entered (or falls back if input ends). */
    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("\n(no more input, using default: my zoo)");
                return "my zoo";
            }
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("  -> Invalid: the name must not be empty.");
        }
    }

    /** Asks until a strictly positive integer is entered (or falls back if input ends). */
    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("\n(no more input, using default: 20)");
                return 20;
            }
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value > 0) {
                    return value;
                }
                System.out.println("  -> Invalid: the number must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("  -> Invalid: '" + line + "' is not an integer.");
            }
        }
    }

    // ------------------------------------------------------------------
    // Prosit 3: classes, constructors, toString, add/search/remove...
    // ------------------------------------------------------------------
    private static void prosit3() {
        title("PROSIT 3 - Animal & Zoo");

        // Instruction 5: default constructor + setters (validation applies)
        Animal lion = new Animal();
        lion.setFamily("Felidae");
        lion.setName("Simba");
        lion.setAge(5);
        lion.setMammal(true);

        // Instruction 6-7: parameterized constructors
        Zoo myZoo = new Zoo("Belvedere", "Tunis");

        // Instruction 8-9: displayZoo() and toString()
        myZoo.displayZoo();
        System.out.println(myZoo);
        System.out.println(lion);

        // Instruction 18: validation through encapsulation
        subtitle("Encapsulation / validation");
        try {
            lion.setAge(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            myZoo.setName("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // Instruction 10-12: add, display, search, uniqueness
        subtitle("Add / display / search / uniqueness");
        Animal elephant = new Animal("Elephantidae", "Dumbo", 12, true);
        Animal zebra = new Animal("Equidae", "Marty", 7, true);
        System.out.println("Add Simba     -> " + myZoo.addAnimal(lion));
        System.out.println("Add Dumbo     -> " + myZoo.addAnimal(elephant));
        System.out.println("Add Marty     -> " + myZoo.addAnimal(zebra));
        System.out.println("Add Simba again (duplicate) -> "
                + myZoo.addAnimal(new Animal("Felidae", "Simba", 5, true)));
        myZoo.displayAnimals();
        System.out.println("Index of Dumbo         : " + myZoo.searchAnimal(elephant));
        System.out.println("Index of 'marty' (name): " + myZoo.searchAnimalByName("marty"));
        System.out.println("Index of unknown animal: "
                + myZoo.searchAnimal(new Animal("Ursidae", "Baloo", 9, true)));

        // Instruction 13: removal with shifting
        subtitle("Removal");
        System.out.println("Remove Dumbo   -> " + myZoo.removeAnimal(elephant));
        System.out.println("Remove Dumbo again -> " + myZoo.removeAnimal(elephant));
        myZoo.displayAnimals();

        // Instruction 10 / 15 / 17: capacity, isZooFull(), comparerZoo()
        subtitle("Capacity & zoo comparison");
        Zoo bigZoo = new Zoo("Safari", "Hammamet");
        int added = 0;
        for (int i = 1; i <= 30; i++) {
            if (bigZoo.addAnimal(new Animal("Family", "Animal-" + i, i, i % 2 == 0))) {
                added++;
            }
        }
        System.out.println("Tried to add 30 animals, " + added + " accepted.");
        System.out.println("Is Safari full?    " + bigZoo.isZooFull());
        System.out.println("Is Belvedere full? " + myZoo.isZooFull());
        Zoo winner = Zoo.comparerZoo(myZoo, bigZoo);
        System.out.println("Zoo with more animals: " + winner.getName());
    }

    // ------------------------------------------------------------------
    // Prosits 5 & 6: inheritance, polymorphism, equals
    // ------------------------------------------------------------------
    private static void prosit5And6() {
        title("PROSITS 5 & 6 - Inheritance & polymorphism");

        Zoo zoo = new Zoo("Aqua Park", "Sousse");

        Dolphin flipper = new Dolphin("Delphinidae", "Flipper", 8, true, "Ocean", 37.5f);
        Dolphin echo = new Dolphin("Delphinidae", "Echo", 5, true, "Lagoon", 32.0f);
        Penguin pingu = new Penguin("Spheniscidae", "Pingu", 4, false, "Antarctica", 120.0f);
        Penguin skipper = new Penguin("Spheniscidae", "Skipper", 6, false, "Antarctica", 180.5f);
        Terrestrial tiger = new Terrestrial("Felidae", "Shere Khan", 9, true, 4);

        // Instruction 23: overridden toString() shows inherited + specific attributes
        subtitle("toString() of each subclass");
        System.out.println(tiger);
        System.out.println(new Aquatic("Delphinidae", "Generic", 3, true, "Sea"));
        System.out.println(flipper);
        System.out.println(pingu);

        // Polymorphism: subclasses can be stored as Animal in the general array
        subtitle("Subclasses in the general animal array");
        System.out.println("Add tiger (Terrestrial) -> " + zoo.addAnimal(tiger));
        System.out.println("Add flipper (Dolphin)   -> " + zoo.addAnimal(flipper));
        zoo.displayAnimals();

        // Instruction 25: aquatic array
        subtitle("Aquatic array");
        System.out.println("Add Flipper -> " + zoo.addAquaticAnimal(flipper));
        System.out.println("Add Echo    -> " + zoo.addAquaticAnimal(echo));
        System.out.println("Add Pingu   -> " + zoo.addAquaticAnimal(pingu));
        System.out.println("Add Skipper -> " + zoo.addAquaticAnimal(skipper));

        // Instruction 24 & 26: dynamic dispatch on swim()
        subtitle("Polymorphic swim()");
        zoo.makeAquaticSwim();
        new Aquatic("Delphinidae", "Generic", 3, true, "Sea").swim(); // default behavior

        // Instruction 27 & 28: queries on the aquatic array
        subtitle("Queries on aquatic animals");
        System.out.println("Max penguin swimming depth: " + zoo.maxSwimmingDepth());
        zoo.displayNumberOfAquaticByType();

        // Instruction 31: equals() on Aquatic (name + age + habitat)
        subtitle("Aquatic.equals()");
        Dolphin flipperTwin = new Dolphin("OtherFamily", "Flipper", 8, false, "Ocean", 10f);
        Dolphin flipperOlder = new Dolphin("Delphinidae", "Flipper", 9, true, "Ocean", 37.5f);
        System.out.println("Same name/age/habitat (other family & speed) -> "
                + flipper.equals(flipperTwin));
        System.out.println("Different age                                -> "
                + flipper.equals(flipperOlder));
        System.out.println("Adding the twin as a duplicate               -> "
                + zoo.addAquaticAnimal(flipperTwin));

        // Capacity limit of the aquatic array (max 10)
        subtitle("Aquatic capacity (max 10)");
        int extra = 0;
        for (int i = 1; i <= 10; i++) {
            if (zoo.addAquaticAnimal(new Penguin("Spheniscidae", "P" + i, i, false, "Pole", i * 10f))) {
                extra++;
            }
        }
        System.out.println("Tried to add 10 more penguins, " + extra + " accepted (aquatic total: "
                + zoo.getAquaticCount() + ").");
        System.out.println(zoo);
    }

    // ------------------------------------------------------------------
    // Console helpers
    // ------------------------------------------------------------------
    private static void title(String text) {
        System.out.println("\n==================================================");
        System.out.println(text);
        System.out.println("==================================================");
    }

    private static void subtitle(String text) {
        System.out.println("\n--- " + text + " ---");
    }
}
