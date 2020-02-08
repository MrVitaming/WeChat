package lwm.pojo;

public class AccessToken {

	private String accessToken;
	private int expires_in;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public AccessToken() {
		// TODO Auto-generated constructor stub
	}
	public AccessToken(String accessToken, int expires_in) {
		super();
		this.accessToken = accessToken;
		this.expires_in = expires_in;
	}
	
}
