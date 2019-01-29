package io.thorntail.example;


import javax.enterprise.context.ApplicationScoped;

import com.google.common.cache.Cache;


@ApplicationScoped
public class NeedsGuava
{
	@SuppressWarnings("unused")
	private Cache<?, ?> cache;
}
