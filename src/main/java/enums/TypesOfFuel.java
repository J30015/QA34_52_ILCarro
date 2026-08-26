package enums;

public enum TypesOfFuel {
    FUEL(new String[]{"Diesel","Petrol","Hybrid","Electric","Gas"});

    private String[] types;

    TypesOfFuel(String[] types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }
}
