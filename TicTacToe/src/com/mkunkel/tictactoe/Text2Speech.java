package com.mkunkel.tictactoe;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class Text2Speech {
	String speaktext;

	public void dospeak(String speak, String voicename) {
		speaktext = speak;
		String voiceName = voicename;
		try {
			SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
			Synthesizer synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();
			synthesizer.resume();
			desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = desc.getVoices();
			Voice voice = null;
			for (int i = 0; i < voices.length; i++) {
				if (voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
			System.out.print("Speaking : " + speaktext);
			synthesizer.speakPlainText(speaktext, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			synthesizer.deallocate();
		} catch (Exception e) {
			String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
			System.out.println("" + e);
			System.out.println(message);
		}
	}

	public static void main(String[] args) {
		Text2Speech obj = new Text2Speech();
		obj.dospeak(
				"Greetings Doctor Koonkel. Interesting game. Seems like the only move is not to play. How about a nice game of chess. ",
				"kevin16");
	}
}
