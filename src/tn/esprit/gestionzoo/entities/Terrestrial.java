package tn.esprit.gestionzoo.entities;

import java.util.Objects;

/** An animal that lives on land and walks on a given number of legs. */
public class Terrestrial extends Animal {

    protected int nbrLegs;

    public Terrestrial() {
        super();
    }

    public Terrestrial(String family, String name, int age, boolean isMammal, int nbrLegs) {
        super(family, name, age, isMammal);
        setNbrLegs(nbrLegs);
    }

    public int getNbrLegs() {
        return nbrLegs;
    }

    public void setNbrLegs(int nbrLegs) {
        if (nbrLegs < 0) {
            throw new IllegalArgumentException("Number of legs cannot be negative: " + nbrLegs);
        }
        this.nbrLegs = nbrLegs;
    }

    @Override
    public String toString() {
        return "Terrestrial{" + super.toString() + ", nbrLegs=" + nbrLegs + "}";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && nbrLegs == ((Terrestrial) o).nbrLegs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nbrLegs);
    }
}
