public class Client {
    private int clientID;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private int clientAddressID;
aaaaaaaaaaaaaaaaaa
    public Client(){}

    public Client(int clientID, String name, String surname, String email,
    String phoneNumber, int clientAddressID){
        this.clientID = clientID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.clientAddressID = clientAddressID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getClientAddressID() {
        return clientAddressID;
    }

    public void setClientAddressID(int clientAddressID) {
        this.clientAddressID = clientAddressID;
    }
}
