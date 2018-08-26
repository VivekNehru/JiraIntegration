package integrate;

import static org.hamcrest.Matcher.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.http.HttpResponse;
import org.hamcrest.core.IsEqual;

public class Integration2 {

	public static List Projects;
	public static String Message;
	@Test
	
	public void login() {
	
		RestAssured.baseURI = "http://localhost:8089";
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("vivek");
		authScheme.setPassword("Jira.com");
		RestAssured.authentication = authScheme;
		
		int resp = get("http://localhost:8089/rest/api/latest/issue/MYP-14").statusCode();
		System.out.println("success");
		System.out.println(resp);
	}

}

