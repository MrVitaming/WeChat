package lwm.pojo;

public class MPMessage extends Message{

	private MPNews mpnews;
	private int send_ignore_reprint;
	
	public MPNews getMpnews() {
		return mpnews;
	}
	public void setMpnews(MPNews mpnews) {
		this.mpnews = mpnews;
	}
	public int getSend_ignore_reprint() {
		return send_ignore_reprint;
	}
	public void setSend_ignore_reprint(int send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}
	
	
}
