package apiautomationpractices;

import org.testng.Assert;
import MockJsonResponse.JsonResponse;
import io.restassured.path.json.JsonPath;

public class HandlingMockJson {

    public static void main(String[] args) {

        // Convert mock JSON response to JsonPath object
        JsonPath js = new JsonPath(JsonResponse.MockJsonResponse());

        // 1. Print No of courses returned by API
        int noOfCourses = js.getInt("courses.size()");
        System.out.println("No. of courses: " + noOfCourses);
        Assert.assertEquals(noOfCourses, 3);
        System.out.println("[PASS] 1. Print No of courses returned by API");

        // 2. Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount: ₹" + purchaseAmount);
        Assert.assertEquals(purchaseAmount, 910);
        System.out.println("[PASS] 2. Print Purchase Amount");

        // 3. Print Title of the first course
        String titleFirstCourse = js.getString("courses[0].title");
        System.out.println("Title of First Course: " + titleFirstCourse);
        Assert.assertEquals(titleFirstCourse, "Selenium Python"); // optional
        System.out.println("[PASS] 3. Print Title of the first course");

        // 4. Print All course titles and their respective Prices
        System.out.println("4. Course Titles and Prices:");
        for (int i = 0; i < noOfCourses; i++) {
            String title = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println((i + 1) + ". " + title + " — ₹" + price);
        }

        // 5. Print no of copies sold by RPA Course
        int noOfCopiesSoldByRpa = js.getInt("courses[2].copies");
        System.out.println("Copies sold for RPA: " + noOfCopiesSoldByRpa);
        Assert.assertEquals(noOfCopiesSoldByRpa, 10);
        System.out.println("[PASS] 5. Print no of copies sold by RPA Course");

        // 6. Verify if Sum of all Course prices * copies matches Purchase Amount
        int totalSum = 0;

        for (int i = 0; i < noOfCourses; i++) {
            int copies = js.getInt("courses[" + i + "].copies");
            int price = js.getInt("courses[" + i + "].price");
            totalSum += copies * price;
        }

        System.out.println("Calculated total revenue: ₹" + totalSum);
        Assert.assertEquals(totalSum, purchaseAmount);
        System.out.println("[PASS] 6. Sum of all Course prices matches with Purchase Amount");
    }
}
