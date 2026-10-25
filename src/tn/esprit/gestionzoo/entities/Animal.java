package tn.esprit.gestionzoo.entities;

import java.util.Objects;

/**
 * Base class of every animal living in the zoo.
 *
 * <p>Attributes are {@code protected} so that subclasses (Aquatic, Terrestrial...)
 * can use them directly, while setters enforce the business validations
 * (non-blank text, non-negative age).</p>
 */
public class Animal {

    protected String family;
    protected String name;
    protected int age;
    protected boolean isMammal;

    /** Default constructor: fields are filled later through the setters. */
    public Animal() {
    }

    /**
     * Parameterized constructor. Goes through the setters so that
     * validation rules are applied from the very first assignment.
     */
    public Animal(String family, String name, int age, boolean isMammal) {
        setFamily(family);
        setName(name);
        setAge(age);
        setMammal(isMammal);
    }

    // ------------------------------------------------------------------
    // Getters / setters (with validation)
    // ------------------------------------------------------------------

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        if (family == null || family.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal family cannot be empty.");
        }
        this.family = family.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal name cannot be empty.");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Animal age cannot be negative: " + age);
        }
        this.age = age;
    }

    public boolean isMammal() {
        return isMammal;
    }

    public void setMammal(boolean mammal) {
        this.isMammal = mammal;
    }

    // ------------------------------------------------------------------
    // Object overrides
    // ------------------------------------------------------------------

    @Override
    public String toString() {
        return "Animal{family='" + family + "', name='" + name + "', age=" + age
                + ", isMammal=" + isMammal + "}";
    }

    /**
     * Two plain animals are equal when all their attributes match and they
     * are of the exact same class. (Aquatic overrides this with its own rule.)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal other = (Animal) o;
        return age == other.age
                && isMammal == other.isMammal
                && Objects.equals(family, other.family)
                && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, name, age, isMammal);
    }
}
