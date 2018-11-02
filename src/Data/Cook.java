package Data;

public class Cook {
	/*
	 * "count": 11227, "fcount": 8, "food": "童子鸡,青花椒,小土豆,酱油,料酒,红糖,水淀粉", "id":
	 * 68141, "img": "img/cook/000068141.jpg", "name": "青花椒炖童子鸡", "rcount": 0,
	 * "tag": "鸡肉,鸡肉类,家常小炒"
	 */
	int count, id;
	String name, img, tag, food;

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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

}
