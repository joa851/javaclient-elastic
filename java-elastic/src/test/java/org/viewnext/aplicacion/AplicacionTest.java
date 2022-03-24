package org.viewnext.aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.opentest4j.AssertionFailedError;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.SearchResponse;

class AplicacionTest {

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
	
//	@org.junit.jupiter.api.Test
//	void TestInsercionBusqueda() throws ElasticsearchException, IOException {
//		ElasticsearchClient cliente = Aplicacion.iniciarConexion();
//		Producto producto = new Producto("abc", "codigo", 42.0);
//		cliente.index(a -> a.index("test").id("junit").document(producto));	
//		SearchResponse<Producto> search = cliente
//				.search(b -> b.index("test").query(q -> q.term(t -> t.field("name").value("codigo"))), Producto.class);
//		assertTrue(search.hits().hits().get(0).equals(producto));
//	}

}
