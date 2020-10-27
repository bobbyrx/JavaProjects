public abstract class ClassWow {
    private String name;
    private int intellectStart;
    private int strengthStart;
    private int agilityStart;
    private String role;

    public ClassWow(String name, int intellectStart, int strengthStart, int agilityStart, String role) {
        this.name = name;
        this.intellectStart = intellectStart;
        this.strengthStart = strengthStart;
        this.agilityStart = agilityStart;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getIntellectStart() {
        return intellectStart;
    }

    public int getStrengthStart() {
        return strengthStart;
    }

    public int getAgilityStart() {
        return agilityStart;
    }

    public String getRole() {
        return role;
    }

    public abstract void print();
}
