package Data;

public class Check {
	/*
	 *  "count": 3356,
            "fcount": 0,
            "id": 2761,
            "img": "img/check/20150131092036_312.jpg",
            "menu": "其他检查",
            "name": "胸腔积液检查",
            "rcount": 0
	 */
	int count,id;
	String name,img,menu;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
