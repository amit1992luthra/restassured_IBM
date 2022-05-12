package IBM_May.RestAssured1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public void testcase2(ITestContext obj12)
	{
		System.out.println("hello team"); 
		Response resp = RestAssured.delete("http://localhost:3000/sample/5");
		System.out.println(resp.statusCode());
		System.out.println(resp.statusLine());
		System.out.println(resp.asPrettyString());
		System.out.println(obj12.getAttribute("name").toString());
		
				
	}
	
	@Test(enabled = false)
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
	
	@Test(enabled = false)
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testcase5()
	{
		RestAssured.baseURI = "http://localhost:3000";
		
		JSONObject parent = new JSONObject();
		JSONObject categoryobj = new JSONObject();
		JSONObject tagsobj = new JSONObject();
		
		parent.put("id",1234);
		parent.put("name", "shadow");
		parent.put("status", "available");
		
		categoryobj.put("id", "123");
		categoryobj.put("name", "pet");
		
		tagsobj.put("id", 456);
		tagsobj.put("name", "cat");
		
	/*	parent.put("category",categoryobj);
		parent.put("tags", tagsobj);
		
		JSONArray arr = new JSONArray();  //photourls 
		JSONArray tags = new JSONArray();  //tags
		
		arr.add("image1");
		arr.add("image 2");
		arr.add("3");
		
		tags.add(tagsobj);
		
		
	
		parent.put("tags", tags);
		
		parent.put("photoUrls", arr);
		
		*/
		
	
		
		
		System.out.println(parent.toJSONString());
		System.out.println(categoryobj.toJSONString());
		System.out.println(tagsobj.toJSONString());
		
		
		
		
		
		
		
		//System.out.println(obj.toJSONString());
	
		
		
		
		
	/*given()
		.header("content-type","application/json")
		.body(parent.toJSONString()).
	when()
		.post("/sample").
	then()
		.statusCode(201).log().all();*/
		
		
	}
		
		
	
	
	
	
	
}
