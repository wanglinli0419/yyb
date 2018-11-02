package Data;

public class News {
	/*
	 * "author": "yi18.net", 
	 * 浏览次数 "count": 1029, 
	 * "fcount": 0, 
	 * "focal": 0,
	 *  "id": 7486, 
	 * "img": "img/news/20150727174424_994.jpg", 
	 * "md": "6cfa6a80ea78128712c00b671dd84d84", 
	 * "rcount": 0, 
	 * "tag": "社会热点", 
	 * "time": * "Jul 27, 2015 5:44:24 PM",
	 *  "title":
	 */
	int count, id;
	String img, tag, time, title;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
