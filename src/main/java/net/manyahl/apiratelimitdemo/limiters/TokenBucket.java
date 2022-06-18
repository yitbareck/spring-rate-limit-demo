package net.manyahl.apiratelimitdemo.limiters;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Component
public class TokenBucket implements Limiter {
	
	private Bucket bucket;
	
	public TokenBucket(@Value("${rateLimit.numberOfRequests}")long numberOfRequests, @Value("${rateLimit.minutes}")long minutes) {
		bucket = Bucket.builder().addLimit(Bandwidth.classic(numberOfRequests,Refill.greedy(numberOfRequests, Duration.ofMinutes(minutes)))).build();
	}

	@Override
	public boolean isRequestValid() {
		return bucket.tryConsume(1);
	}

}
