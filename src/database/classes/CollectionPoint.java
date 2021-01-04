package database.classes;

public class CollectionPoint {
    private int collectionPointID;
    private String name;
    private Address address;


    public CollectionPoint() {
    }

    public CollectionPoint(int collectionPointID, String name, Address address) {
        this.collectionPointID = collectionPointID;
        this.name = name;
        this.address = address;
    }

    public int getCollectionPointID() {
        return this.collectionPointID;
    }

    public String getCollectionPointID_S() {
        return Converter.IntToString(collectionPointID);
    }

    public void setCollectionPointID(int collectionPointID) {
        this.collectionPointID = collectionPointID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getAddress_S() {
        return this.address.getFullAddress();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
