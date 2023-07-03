package bca.advancejava.model;
public class Employee {
    private int Id;
    private String Name;
    private String Gender;
    private String Department;
    private int Salary;
    private String Address;
    private byte[] ProfilePic;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }


    public byte[] getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        ProfilePic = profilePic;
    }

    public Employee(int id, String name, String gender, String department, int salary, String address, byte[] profilePic) {
        Id = id;
        Name = name;
        Gender = gender;
        Department = department;
        Salary = salary;
        Address = address;
        ProfilePic = profilePic;
    }
}
