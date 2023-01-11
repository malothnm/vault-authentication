package org.acme;

import io.quarkus.vault.VaultKVSecretEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/vault/test")
public class VaultResource {

    private static Logger logger = LoggerFactory.getLogger(VaultResource.class);
    @Inject
    VaultKVSecretEngine kvSecretEngine;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{org}/{product}")
    public Map<String,String> getSecrets(@PathParam("org") Integer org, @PathParam("product") Integer product){

        String keyPath = "keys/symmetric/" + org.toString() + "/" + product.toString();
        logger.info(keyPath);
        return kvSecretEngine.readSecret(keyPath);

    }
}
