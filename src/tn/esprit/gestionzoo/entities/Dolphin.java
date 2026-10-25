package tn.esprit.gestionzoo.entities;

/** A dolphin: an aquatic mammal characterized by its swimming speed. */
public class Dolphin extends Aquatic {

    protected float swimmingSpeed;

    public Dolphin() {
        super();
    }

    public Dolphin(String family, String name, int age, boolean isMammal,
                   String habitat, float swimmingSpeed) {
        super(family, name, age, isMammal, habitat);
        setSwimmingSpeed(swimmingSpeed);
    }

    public float getSwimmingSpeed() {
        return swimmingSpeed;
    }

    public void setSwimmingSpeed(float swimmingSpeed) {
        if (swimmingSpeed < 0) {
            throw new IllegalArgumentException("Swimming speed cannot be negative: " + swimmingSpeed);
        }
        this.swimmingSpeed = swimmingSpeed;
    }

    @Override
    public void swim() {
        System.out.println("This dolphin is swimming.");
    }

    @Override
    public String toString() {
        return "Dolphin{" + super.toString() + ", swimmingSpeed=" + swimmingSpeed + "}";
    }
}
