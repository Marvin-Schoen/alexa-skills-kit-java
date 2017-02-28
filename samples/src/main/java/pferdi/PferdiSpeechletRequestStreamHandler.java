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
		supportedApplicationIds.add("amzn1.ask.skill.b6eb7cbc-4878-4bad-be4d-2c82f0e6cae2");
		supportedApplicationIds.add("amzn1.ask.skill.ac7a2dd6-b1dd-4858-8a71-4e62d0e2d399");
	}

	public PferdiSpeechletRequestStreamHandler() {
		super(new PferdiSpeechlet(), supportedApplicationIds);
	}

}
