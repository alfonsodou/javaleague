package org.javahispano.javaleague.client.place;

public class NameTokens {
    public static final String HOME = "/home";
    // Login Page
    public static final String LOGIN = "/login";
    
    // Tournament Page
    public static final String TOURNAMENT = "/tournament";
    
    // Downloads Page
    public static final String DOWNLOADS = "/downloads";
    
    // Rules Page
    public static final String RULES = "/rules";
    
    // Authenticate User Page
    public static final String AUTHENTICATE = "/authenticate";
    
    public static final String UNAUTHORIZED = "/unauthorized";
    
    // Getters for UiBinders

    public static String getHome() {
        return HOME;
    }
    
    public static String getLogin() {
    	return LOGIN;
    }


	public static String getTournament() {
		return TOURNAMENT;
	}

	public static String getDownloads() {
		return DOWNLOADS;
	}

	public static String getRules() {
		return RULES;
	}

	public static String getAuthenticate() {
		return AUTHENTICATE;
	}

	public static String getUnauthorized() {
		return UNAUTHORIZED;
	}
	
	
}