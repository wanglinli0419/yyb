package Data;

public class Drug {
	/*
	 * 		"PType": "解热镇痛",
            "category": 182,
            "count": 3993,
            "factory": "鲁南贝特制药有限公司",
            "fcount": 0,
            "id": 24837,
            "image": "img/drug/20150313102024_173.jpg",
            "name": "吉松",
            "price": 0.0,
            "rcount": 0
	 */
	int id,count;
	String name,PType,image,factory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPType() {
		return PType;
	}
	public void setPType(String pType) {
		PType = pType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	
}
