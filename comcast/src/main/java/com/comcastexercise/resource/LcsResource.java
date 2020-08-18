package com.comcastexercise.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.comcastexercise.LargestCommonSubstring;
import com.comcastexercise.Model.LcsRequestModel;
import com.comcastexercise.Model.LcsResponseModel;
import com.comcastexercise.Model.LcsStringModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/service")
public class LcsResource {

	private LcsRequestModel lcsRequestModel;
	private LcsResponseModel lcsResponseModel;
	
	
/* POST method of the resource to perform LCS.   
 * LcsRequestModel is object for holding input data
 * LcsResponseModel is for response. This is to have different JSON string.
 * 
 */
	@POST
	@Path("/lcs")
	@Produces("application/json")
	@Consumes("application/json")
	public Response largestCommonString(String json) {

		ObjectMapper mapper = new ObjectMapper();
		String responseJson = new String();

		try {
			
			// Convert JSON to LcsRequestObject model using Jackson library . Also json validation and create response if invalid
			Response validationResponse = validateInput(json, mapper);
			if (validationResponse != null) {
				return validationResponse;
			}
			
			// call to perform LCS algorithm
			LcsStringModel lcs = LargestCommonSubstring.getLargestCommonSubstringforList(lcsRequestModel.getSetOfStrings());
			
			// set LcsResponseModel
			getLcsResponseModel().setLcs(lcs);
			responseJson = mapper.writeValueAsString(getLcsResponseModel());
			return Response.status(Response.Status.OK).entity(responseJson).build();

		} catch (Exception exception) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(buildExceptionMessage(exception.getMessage())).build();
		}

	}

	private Response validateInput(String json, ObjectMapper mapper) {
		try {
			lcsRequestModel = (LcsRequestModel) mapper.readValue(json, LcsRequestModel.class);
			if (lcsRequestModel.getSetOfStrings().isEmpty())
				return Response.status(Response.Status.NO_CONTENT)
						.entity(buildExceptionMessage("List of Strings is empty")).build();

		} catch (JsonParseException jpe) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(buildExceptionMessage(jpe.getMessage()))
					.build();
		} catch (JsonMappingException jme) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(buildExceptionMessage(jme.getMessage()))
					.build();
		} catch (IOException exception) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(buildExceptionMessage(exception.getMessage())).build();
		}
		return null;
	}

	private String buildExceptionMessage(String message) {
		return "{ \"exception\" : \"" + message + "\" } ";
	}

	public LcsResponseModel getLcsResponseModel() {
		if (lcsResponseModel == null) {
			lcsResponseModel = new LcsResponseModel();
		}
		return lcsResponseModel;
	}

	public LcsRequestModel getLcsRequestModel() {
		if (lcsRequestModel == null) {
			lcsRequestModel = new LcsRequestModel();
		}
		return lcsRequestModel;
	}

}
