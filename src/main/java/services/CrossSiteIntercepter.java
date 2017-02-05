package services;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider
public class CrossSiteIntercepter implements WriterInterceptor {

	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		MultivaluedMap<String, Object> headers = context.getHeaders();
		headers.add("Access-Control-Allow-Origin", "hi");
		context.proceed();
	}
}