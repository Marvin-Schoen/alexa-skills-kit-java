package pferdi;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class PferdiSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	static {
		/*
		 * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
		 * Alexa Skill and put the relevant Application Ids in this Set.
		 */
		supportedApplicationIds.add("amzn1.ask.skill.b6eb7cbc-4878-4bad-be4d-2c82f0e6cae2 ");
	}

	public PferdiSpeechletRequestStreamHandler() {
		super(new PferdiSpeechlet(), supportedApplicationIds);
	}

}
