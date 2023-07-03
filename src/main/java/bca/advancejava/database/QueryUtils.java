package bca.advancejava.database;

public class QueryUtils {
    public static String insertQuery() {
        return "INSERT INTO employee_info(id,name,address,gender,department,salary,profilePic) VALUES(?,?,?,?,?,?,?)";
    }

    public static String selectAllQuery() {
        return "SELECT * FROM employee_info";
    }

    public static String deleteQuery(int id) {
        return "DELETE FROM employee_info WHERE id = " + id;
    }
    public static String updateQuery(int id){
        return "UPDATE employee_info SET name=?,address=?,gender=?,department=?,salary=?,profilePic=? WHERE id = " + id;
    }
    public static String getByIdQuery(int id) {
        return "SELECT * FROM employee_info WHERE id = " + id;
    }


}
