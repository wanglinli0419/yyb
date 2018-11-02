package Data;

public class CollectData {
	
	int typeflag;
	String url,title;
	public CollectData(int typeflag, String url, String title) {
		super();
		this.typeflag = typeflag;
		this.url = url;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public int getTypeflag() {
		return typeflag;
	}
	public String getUrl() {
		return url;
	}
	
}
