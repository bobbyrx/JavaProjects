public class Priest extends ClassWow {
    private boolean isShadowFormActive;

    public Priest(boolean isShadowFormActive) {
        super("Priest", 25, 15, 15, "Support");
        this.isShadowFormActive = isShadowFormActive;
    }

    public String getIsShadowFormActive() {
        if(isShadowFormActive)return "true";
        else return "false";
    }

    public boolean isShadowFormActive() {
        return isShadowFormActive;
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Priest stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println("Active shadow form: "+this.getIsShadowFormActive());
        System.out.println();
    }

    public void activateShadowForm() {
        if(isShadowFormActive) System.out.println("Shadow form is already activated");
        else{
            isShadowFormActive=true;
            System.out.println("Activated shadow form");
        }
    }

}
