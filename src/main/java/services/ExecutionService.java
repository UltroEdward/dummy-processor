package services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ExecutionDto;

@Path("/execution")
public class ExecutionService {

	protected static final Logger LOG = LoggerFactory.getLogger(ExecutionService.class);

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getExecution(@PathParam("id") long id) {
		LOG.info("hello");
		return null;
	}

	@GET
	@Path("/start/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExecutionDto runExecution(@PathParam("id") long id) {
		LOG.info("hello");
		return null;
	}

	@GET
	@Path("/stop/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExecutionDto stopExecution(@PathParam("id") long id) {
		LOG.info("hello");
		return null;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ExecutionDto createExecution(String payLoad) {
		LOG.info(payLoad);
		return null;
	}

}