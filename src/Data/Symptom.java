package Data;

public class Symptom {
	/*
	 *"count": 2984,
            "fcount": 0,
            "id": 3513,
            "img": "img/symptom/20150131091822_615.jpg",
            "name": "药物性胸腔积液",
            "place": "乳房",
            "rcount": 0
	 */
	int count,id;
	String name,img,place;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
}
