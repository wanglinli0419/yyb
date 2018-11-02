package Data;

public class Hospitial {
	/*
	  "address": "苏州市沧浪区南环西路36号",
            "area": 208,
            "comment": 6.0,
            "count": 3232,
            "domain": "sztjyy",
            "fax": "",
            "gobus": "<p> </p>",
            "id": 1,
            "level": "二级甲等",
            "logo": "img/hospital/00001.jpg",
            "mail": "",
            "mtype": "非医保",
            "name": "苏州同济医院",
            "nature": "营利性",
            "tel": "400-6009-222",
            "url": "http://www.sztjyy.cn",
            "x": 120.615,
            "y": 31.279,
            "zipcode": "215000"
	 */
	float x,y;
	int id;
	String address,level,logo,name,nature;
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	
}
