package payloadData;

public class PayLoad {

	public static String addEmployeeDetails(String empName,String jobRole) {
		String str="{\r\n"
				+ "    \"name\": \""+empName+"\",\r\n"
				+ "    \"job\": \""+jobRole+"\"\r\n"
				+ "}";
		return str;
	}
}
