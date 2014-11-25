package com.mooneyserver.dublinpubs.rest;

import com.mooneyserver.dublinpubs.controller.PubController;
import com.mooneyserver.dublinpubs.models.Pub;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Optional;

@Path("pubs")
@Produces(MediaType.APPLICATION_JSON)
public class PubResource {

    @Inject
    private PubController pubController;
    @Context
    private UriInfo uriInfo;

    @GET
    public Response getAllPubs() {
        return Response
              .ok(pubController.getAllPubs())
              .build();
    }

    @GET
    @Path("/{id}")
    public Response getPub(@PathParam("id") Long pubId) {
        Response response;
        Optional<Pub> optPub = pubController.getPubById(pubId);
        if (optPub.isPresent()) {
            response = Response.ok(optPub.get()).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addNewPub(@Valid Pub pub) {
        Pub persistedPub = pubController.savePub(pub);
        return Response
              .created(buildUriForEntity(persistedPub))
              .build();
    }

    URI buildUriForEntity(Pub pub) {
        return UriBuilder
              .fromUri(
                    uriInfo.getAbsolutePath()
              ).path(
                    String.valueOf(pub.getId())
              ).build();
    }
}
