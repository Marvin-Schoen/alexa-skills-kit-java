package pferdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

public class PferdiSpeechlet implements Speechlet {
	private static final Logger log = LoggerFactory.getLogger(PferdiSpeechlet.class);

	private int zaehler = 0;
	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if ("HelloWorldIntent".equals(intentName)) {
			return getHelloResponse();
		} else if ("QuestionIntent".equals(intentName)) {
			return getQuestionIntentResponse();
		} else if ("AMAZON.HelpIntent".equals(intentName)) {
			return getHelpResponse();
		} else if ("MehrereZeitraumeIntent".equals(intentName)) {
			return getMehrereZeitraumeResponse();
		} else {
			throw new SpeechletException("Invalid Intent");
		}
	}

	private SpeechletResponse getMehrereZeitraumeResponse() {

		String speechText = "Zum Jahreswechsel können sich wesentliche Beitragsmerkmale wie zum Beispiel die SF-Klasse, die Regionalklasse oder die Typklasse ändern. Damit Sie die genaue Beitragsunterschiede erkennen führen wir für das jetzige Fahrzeug die einzelnen Zahlungszeiträume und die wesentlichen Beitragsmerkmale auf." + "Für das bisherige Fahrzeug betrachten wir aus anderen Gründen 2 Zeiträume: Der Zeitraum vom 06.01.2016 bis zum 02.06.2016 betrachtet den zuletzt von ihnen gezahlten Beitrag. der Zeitraum vom 03.06.2016 bis 03.09.2016 betrachtet den Zeitraum für die neue Versicherungsperiode zu der Sie bereits eine Rechnung für Ihr bisheriges Fahrzeug erhalten haben.  Den Rechnungsbetrag von 12 Euro schreiben wir Ihnen gut.";

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	private SpeechletResponse getQuestionIntentResponse() {
		zaehler++;
		String speechText = "Das ist deine " + zaehler + ". Frage";
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	private SpeechletResponse getHelloResponse() {
		String speechText = "Hallo Kunde";

		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("HelloWorld");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech, card);
	}

	private SpeechletResponse getHelpResponse() {
		log.info("getHelpResponse aufgerufen");

		String speechText = "Sag: erkläre mir mein Dokument oder stell eine Frage";

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		zaehler = 0;
	}

	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Hallo ich bin Pferdi, das Maskottchen der LVM Versicherung. Frag mich nach deinem Fahrzeug Wechsel Dokument";

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);
		return SpeechletResponse.newTellResponse(speech);
	}

}
