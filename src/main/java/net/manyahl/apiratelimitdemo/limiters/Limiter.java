package net.manyahl.apiratelimitdemo.limiters;

public interface Limiter {
	public boolean isRequestValid();
}
