package models;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import play.mvc.Http.Response;
import play.mvc.results.RenderJson;

public class JacksonUtil {

	static ObjectMapper mapper = new ObjectMapper()
			.configure(
					org.codehaus.jackson.map.SerializationConfig.Feature.REQUIRE_SETTERS_FOR_GETTERS,
					true);

	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{ \"error\" : \"failed to serialize\" }";
	}

	public static void writeJson(Object value, Response response) {
		boolean success = false;
		try {
			mapper.writeValue(response.out, value);
			success = true;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!success)
			throw new RenderJson("{ \"error\" : \"failed to serialize\" }");
	}
}
