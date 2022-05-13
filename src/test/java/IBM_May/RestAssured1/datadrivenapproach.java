package IBM_May.RestAssured1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class datadrivenapproach {

	@Test(dataProvider = "PostRequestdata")
	public void testcase1(String bn, String fn, String place) {

		JSONObject jsonobj = new JSONObject(); // main body object

		jsonobj.put("batchname", bn);
		jsonobj.put("filename", fn);
		jsonobj.put("place", place);

		RestAssured.baseURI = "http://localhost:3000";
		given().contentType(ContentType.JSON).body(jsonobj.toJSONString()).post("/sample").then().statusCode(201)
				.log().all();
	}

	@DataProvider(name = "PostRequestdata")
	public Object[][] postreqdata() throws IOException {

		Object[][] data = datafromexcel.testdata();
		return data;
	}

}
