package IBM_May.RestAssured1;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class day1 {
	
	
	@Test(enabled = false)
	public void testcase1()
	{
		System.out.println("hello team"); 
		Response resp = RestAssured.get("http://localhost:3000/sample/4");
		System.out.println(resp.statusCode());
		System.out.println(resp.statusLine());
		System.out.println(resp.asPrettyString());
				
	}
	
	@Test(enabled = false)
	public void testcase2()
	{
		System.out.println("hello team"); 
		Response resp = RestAssured.delete("http://localhost:3000/sample/5");
		System.out.println(resp.statusCode());
		System.out.println(resp.statusLine());
		System.out.println(resp.asPrettyString());
				
	}
	
	@Test(dependsOnMethods="testcase4")
	public void testcase3(ITestContext obj123)
	{
		RestAssured.baseURI = "http://localhost:3000";
		
		given()
			.get("/sample/"+obj123.getAttribute("postid").toString()).
		then()
			.statusCode(200)
			.log().all();
		
		System.out.println(obj123.getAttribute("name").toString());
	}
	
	@Test
	public void testcase4(ITestContext obj)
	{
		RestAssured.baseURI = "http://localhost:3000";
		String body = "{\"batchname\":\"123456\",\"filename\":\"amit\",\"place\":\"chennai\"}";
		
		
		 Response resp = given()
			.header("content-type","application/json")
			//.contentType(ContentType.JSON)
			.body(body).
		when()
			.post("/sample").
		then()
			.statusCode(201).log().all().extract().response();
		 
		 String idval = resp.jsonPath().getString("id");
		 String name = resp.jsonPath().getString("filename");
		 obj.setAttribute("postid", idval);
		 obj.setAttribute("name", name);
		 
	}
		
		
	
	
	
	
	
}
