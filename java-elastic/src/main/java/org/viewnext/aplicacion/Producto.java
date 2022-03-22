package org.viewnext.aplicacion;

public class Producto {
	private String id;
	private String name;
	private double price;
	
	public Producto(){}
	
	public Producto(String id, String name, double price){
		this.id=id;
		this.name=name;
		this.price=price;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	
	
	
}
