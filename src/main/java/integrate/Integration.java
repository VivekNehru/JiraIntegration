package integrate;

import static org.hamcrest.Matcher.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.http.HttpResponse;
import org.hamcrest.core.IsEqual;

public class Integration {

	public List<String> Projects;
	public static int StatusCode;
	public List<String> Issueid= new LinkedList<String>();
	public String Status="";
	public Map<String,String> AttachMap= new TreeMap<String,String>();
	@Test
	
	public List<String> login(String Username,char[]Password) {
		String pwd = new String(Password);
		RestAssured.baseURI = JIRAUI.SLink;
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(Username);
		authScheme.setPassword(pwd);
		RestAssured.authentication = authScheme;
		
		StatusCode=get(JIRAUI.SLink+"/rest/auth/latest/session").statusCode();
		
		Projects= get(JIRAUI.SLink+"/rest/api/latest/project").jsonPath().getList("name");
		return Projects;
		
	}
	
	@Test
	public List<String> GetIssueKeys(String jql) {
		Response resp=get(JIRAUI.SLink+"/rest/api/latest/search?jql="+jql);
		String totalcount=resp.jsonPath().getString("total");
		Issueid=resp.jsonPath().getList("issues.key");
		StatusCode=resp.getStatusCode();
	return Issueid;
	}
	
	public String CheckAttachmentsInPlan(Object a) {
		
		Response resp= get(JIRAUI.SLink+"/rest/api/latest/issue/"+a);
		StatusCode=resp.getStatusCode();
		List attachid=resp.jsonPath().getList("fields.attachment.id");
		if(!attachid.isEmpty()) {
		Status="Yes";
		}
		else {
			Status="No";
		}
		Response resp2=delete(JIRAUI.SLink+"/rest/auth/latest/session");
		int code=resp2.statusCode();
		String resp3=resp2.asString();
		System.out.println(code+" "+resp3);
		return Status;
	}

}

