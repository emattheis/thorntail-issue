package io.thorntail.example;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationScoped
@ApplicationPath("/")
public class GuavaFail
extends Application
{
	@SuppressWarnings("unused")
	@Inject
	private NeedsGuava needsGuava;
}
