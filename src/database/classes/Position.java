package database.classes;

public class Position {

    private int id;
    private String name;
    private float maxSalary;
    private float minSalary;

    public Position() {}

    public Position(int id, String name, float minSalary, float maxSalary) {
        setId(id);
        setName(name);
        setMinSalary(minSalary);
        setMaxSalary(maxSalary);
    }

    /// Setters ///
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        // check name length limit
        this.name = name;
    }
    public void setMaxSalary(float maxSalary) {
        this.maxSalary = Math.round(maxSalary * 100) / 100;
    }
    public void setMinSalary(float minSalary) {
        this.minSalary = Math.round(minSalary * 100) / 100;
    }

    /// Getters ///
    public int getId() {
        return this.id;
    }
    public String getId_S() {
        return Converter.IntToString(this.id);
    }
    public String getName() {
        return this.name;
    }
    public float getMaxSalary() {
        return this.maxSalary;
    }
    public String getMaxSalary_S() {
        return Converter.DoubleToString(this.maxSalary);
    }
    public float getMinSalary() {
        return this.minSalary;
    }
    public String getMinSalary_S() {
        return Converter.DoubleToString(this.minSalary);
    }
}