package org.viewnext.aplicacion;

import static org.junit.jupiter.api.Assertions.*;

class TestAplicacion {

	@org.junit.jupiter.api.Test
	void TestCreacionProducto() {
		Producto producto1 = new Producto("id", "nombre", 10.00);
		Producto producto2 = new Producto();
		producto2.setId("id");
		producto2.setName("nombre");
		producto2.setPrice(20.00);
		
		assertTrue(producto1.getName().equals(producto2.getName()));
		assertTrue(producto1.getId().equals(producto2.getId()));
		assertTrue(producto1.getPrice()!=producto2.getPrice());
		assertFalse(producto1.equals(producto2));
	}

}
