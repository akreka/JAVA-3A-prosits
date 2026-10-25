package tn.esprit.gestionzoo.entities;

/** A penguin: an aquatic bird characterized by the depth it can dive to. */
public class Penguin extends Aquatic {

    protected float swimmingDepth;

    public Penguin() {
        super();
    }

    public Penguin(String family, String name, int age, boolean isMammal,
                   String habitat, float swimmingDepth) {
        super(family, name, age, isMammal, habitat);
        setSwimmingDepth(swimmingDepth);
    }

    public float getSwimmingDepth() {
        return swimmingDepth;
    }

    public void setSwimmingDepth(float swimmingDepth) {
        if (swimmingDepth < 0) {
            throw new IllegalArgumentException("Swimming depth cannot be negative: " + swimmingDepth);
        }
        this.swimmingDepth = swimmingDepth;
    }

    @Override
    public void swim() {
        System.out.println("This penguin is swimming.");
    }

    @Override
    public String toString() {
        return "Penguin{" + super.toString() + ", swimmingDepth=" + swimmingDepth + "}";
    }
}
