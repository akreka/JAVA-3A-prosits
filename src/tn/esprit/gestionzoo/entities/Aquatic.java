package tn.esprit.gestionzoo.entities;

import java.util.Objects;

/**
 * An animal that lives in water. Provides a default {@link #swim()} behavior
 * that concrete aquatic animals (Dolphin, Penguin) override.
 */
public class Aquatic extends Animal {

    protected String habitat;

    public Aquatic() {
        super();
    }

    public Aquatic(String family, String name, int age, boolean isMammal, String habitat) {
        super(family, name, age, isMammal);
        setHabitat(habitat);
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        if (habitat == null || habitat.trim().isEmpty()) {
            throw new IllegalArgumentException("Habitat cannot be empty.");
        }
        this.habitat = habitat.trim();
    }

    /** Default swimming behavior; subclasses override it (polymorphism). */
    public void swim() {
        System.out.println("This aquatic animal is swimming.");
    }

    @Override
    public String toString() {
        return "Aquatic{" + super.toString() + ", habitat='" + habitat + "'}";
    }

    /**
     * Two aquatic animals are identical when they share the same
     * name, age and habitat (family and mammal flag are ignored).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aquatic)) return false;
        Aquatic other = (Aquatic) o;
        return age == other.age
                && Objects.equals(name, other.name)
                && Objects.equals(habitat, other.habitat);
    }

    /** Kept consistent with {@link #equals(Object)}: same fields only. */
    @Override
    public int hashCode() {
        return Objects.hash(name, age, habitat);
    }
}
