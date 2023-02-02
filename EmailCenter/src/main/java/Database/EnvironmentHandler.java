package Database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Logger;

public final class EnvironmentHandler {

	static Logger logger = Logger.getLogger(EnvironmentHandler.class.getName());

	private EnvironmentHandler(){
		// This class is never to be instantiated
	}

	public static String GET_VARIABLE_BY_KEY(String environmentKey){
		try {
			return System.getenv(environmentKey);
		} catch (Exception e) {
			logger.warning(String.format("Failed to retrieve %s from environment, defaulting to null", environmentKey));
			return null;
		}
	}

	public static Hashtable<String, String> RETRIEVE_ALL_KEYS_FROM_ENVIRONMENT(ArrayList<String> environmentKeys){
		Hashtable<String, String> result = new Hashtable<>();
		for(String environmentKey : environmentKeys){
			result.put(environmentKey, EnvironmentHandler.GET_VARIABLE_BY_KEY(environmentKey));
		}
		return result;
	}
}
