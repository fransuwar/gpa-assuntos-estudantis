package br.ufc.npi.auxilio.test;

import java.util.List;
import java.util.Locale;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

public class StoryBase extends JUnitStory{
	
	@SuppressWarnings("deprecation")
	@Override
	public Configuration configuration() {
		Keywords keywords = new LocalizedKeywords(new Locale("pt", "BR"));
		return new MostUsefulConfiguration().
		useKeywords(keywords).
		useStoryParser(new RegexStoryParser()).
		useStoryLoader(new LoadFromClasspath(this.getClass())).
		useStoryReporterBuilder(new StoryReporterBuilder()
			.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
			.withDefaultFormats()
			.withFormats(Format.HTML, Format.CONSOLE)
			.withFailureTrace(true)
		);
	}
	
	@Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
    }

}
