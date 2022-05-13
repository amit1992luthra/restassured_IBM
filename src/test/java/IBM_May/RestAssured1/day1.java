package IBM_May.RestAssured1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
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
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		    .queryParam("username", "amit")
		    .queryParam("password", "123456789").log().all().
		when()
			.get("/user/login").
		then()
			.statusCode(200)
			.log().all();
		
		//System.out.println(obj123.getAttribute("name").toString());
	}
	
	@Test(enabled = false)
	public void testcase4(ITestContext obj)
	{
		RestAssured.baseURI = "http://localhost:3000";
		String body = "{\"batchname\":\"123456\",\"filename\":\"amit\",\"place\":\"chennai\"}";
		
		String body2 = "{\"id\":9223372036854019000,\"category\":{\"id\":0,\"name\":"+body+"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
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
	@Test(enabled = false)
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
		
		parent.put("category",categoryobj);
		parent.put("tags", tagsobj);
		
		
		
		
		//Adding Photourls array
		JSONArray arr = new JSONArray();  //photourls 
		arr.add("image1");
		arr.add("image 2");
		arr.add("3");
		parent.put("photoUrls", arr);
		
		//Adding tags as array
		JSONArray tags = new JSONArray();  //tags
		tags.add(tagsobj);
		parent.put("tags", tags);

		
		
	
		
		
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
	
	
	@DataProvider(name="requestdata")
	public Object[][] dataprov()
	{
		Object[][] data = new Object[4][3];
		data[0][0] = "batchname1";
		data[0][1]= "filename1";
		data[0][2] = "place1";
		
		data[1][0] = "batchname2";
		data[1][1]= "filename2";
		data[1][2] = "place2";
		
		data[2][0] = "batchname3";
		data[2][1]= "filename3";
		data[2][2] = "place3";
		
		data[3][0] = "batchname4";
		data[3][1]= "filename4";
		data[3][2] = "place4";
		
		return data;
		
	}
	
	
	
	
	
	
	
	@Test(enabled = true,dataProvider = "requestdata")
	public void testcase6(String bn,String fn, String place )
	{
		RestAssured.baseURI = "http://localhost:3000";
		
		JSONObject parent = new JSONObject();
		parent.put("batchname",bn);
		parent.put("filename", fn);
		parent.put("place", place);
		
		
	given()
		.header("content-type","application/json")
		.body(parent.toJSONString()).
	when()
		.post("/sample").
	then()
		.statusCode(201).log().all();
		
		
	}
	
	
		
		
	
	
	
	
	
}
