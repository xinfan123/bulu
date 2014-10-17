package com.xinfan.blueblue.location;

public class LocationEntity {
private Double Latitude;//纬度
private Double Longitude;//经度
private String Address;//地址
private String Provider;//来源
private String province;//省
private String city;//市
private String district;//区
private String town;//镇
private String village;//村
private String street;//街道
private String streetNo;//门号
public Double getLatitude() {
	return Latitude;
}
public void setLatitude(Double latitude) {
	Latitude = latitude;
}
public Double getLongitude() {
	return Longitude;
}
public void setLongitude(Double longitude) {
	Longitude = longitude;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getProvider() {
	return Provider;
}
public void setProvider(String provider) {
	Provider = provider;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getTown() {
	return town;
}
public void setTown(String town) {
	this.town = town;
}
public String getVillage() {
	return village;
}
public void setVillage(String village) {
	this.village = village;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getStreetNo() {
	return streetNo;
}
public void setStreetNo(String streetNo) {
	this.streetNo = streetNo;
}

}
