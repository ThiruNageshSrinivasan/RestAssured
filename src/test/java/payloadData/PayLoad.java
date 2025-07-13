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
	public static String xmlpayload() {
	    String xmlpayload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<Pet>\n" +
	            "  <id>0</id>\n" +
	            "  <Category>\n" +
	            "    <id>0</id>\n" +
	            "    <name>string</name>\n" +
	            "  </Category>\n" +
	            "  <name>doggie</name>\n" +
	            "  <photoUrls>\n" +
	            "    <photoUrl>string</photoUrl>\n" +
	            "  </photoUrls>\n" +
	            "  <tags>\n" +
	            "    <Tag>\n" +
	            "      <id>0</id>\n" +
	            "      <name>string</name>\n" +
	            "    </Tag>\n" +
	            "  </tags>\n" +
	            "  <status>available</status>\n" +
	            "</Pet>";
	    return xmlpayload;
	}

}
