package MockJsonResponse;

public class JsonResponse {
	public static String MockJsonResponse() {

		String response = "{\r\n" + "  \"dashboard\": {\r\n" + "    \"purchaseAmount\": 910,\r\n"
				+ "    \"website\": \"grotechminds.com\"\r\n" + "  },\r\n" + "  \"courses\": [\r\n" + "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n" + "      \"price\": 50,\r\n" + "      \"copies\": 6\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"title\": \"Cypress\",\r\n" + "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n" + "    },\r\n" + "    {\r\n" + "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n" + "      \"copies\": 10\r\n" + "    }\r\n" + "  ]\r\n" + "}";
		return response;
		

	}
}
