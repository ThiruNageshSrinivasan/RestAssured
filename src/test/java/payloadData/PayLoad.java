package payloadData;

public class PayLoad {

	public static String addEmployeeDetails(String empName,String jobRole) {
		String str="{\r\n"
				+ "    \"name\": \""+empName+"\",\r\n"
				+ "    \"job\": \""+jobRole+"\"\r\n"
				+ "}";
		return str;
	}


	public static String getBookDetails(String isbn, String aisle, String author) {
        return "{\n" +
               "  \"name\": \"Learn Appium Automation with Java\",\n" +
               "  \"isbn\": \"" + isbn + "\",\n" +
               "  \"aisle\": \"" + aisle + "\",\n" +
               "  \"author\": \"" + author + "\"\n" +
               "}";
    }
}
