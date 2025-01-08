public class Key extends Item {
    private String keyType;

    public Key(String name, String description, String keyType) {
        super(name, description);
        this.keyType = keyType;
    }

    public String getKeyType() {
        return keyType;
    }

    @Override
    public void use() {
        
        System.out.println("You use the " + getName() + " to unlock a " + keyType + ".");
    }

    @Override
    public String toString() {
        return super.toString() + "\nKey Type: " + keyType;
    }
}
