package org.viewnext.aplicacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.json.JsonObject;

public class Aplicacion {
	private static ElasticsearchClient iniciarConexion() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme"));

		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"))
				.setHttpClientConfigCallback(new HttpClientConfigCallback() {
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						httpClientBuilder.disableAuthCaching();
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				});

		RestClient httpClient = builder.build();
		JacksonJsonpMapper jsonMapper = new JacksonJsonpMapper();
		ElasticsearchTransport transport = new RestClientTransport(httpClient, jsonMapper);

		return new ElasticsearchClient(transport);
	}

	public static void main(String[] args) throws ElasticsearchException, IOException {
		ElasticsearchClient cliente = iniciarConexion();

		Producto producto = new Producto("abc", "codigo", 42.0);
		Producto producto2 = new Producto("def", "purse", 30.0);

		cliente.index(a -> a.index("producto1").id("org-viewnext-java").document(producto));
		cliente.index(a -> a.index("producto2").id(producto.getId()).document(producto2));

		System.out.println("Producto creados e indexados");

		SearchResponse<Producto> searchBag = cliente
				.search(b -> b.index("product").query(q -> q.term(t -> t.field("name").value("bag"))), Producto.class);

		SearchResponse<Producto> searchPurse = cliente.search(
				b -> b.index("product2").query(q -> q.term(t -> t.field("name").value("purse"))), Producto.class);

		Producto productoBag = searchBag.hits().hits().get(0).source();
		System.out.println(productoBag.getId());
		System.out.println(productoBag.getPrice());

		Producto productoPurse = searchPurse.hits().hits().get(0).source();
		System.out.println(productoPurse.getId());
		System.out.println(productoPurse.getPrice());

	}

//	

}
