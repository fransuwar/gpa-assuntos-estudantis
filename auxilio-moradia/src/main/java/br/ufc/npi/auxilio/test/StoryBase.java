package br.ufc.npi.auxilio.test;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;

public class CadastroSelecaoTest extends JUnitStory{
	
	@SuppressWarnings("deprecation")
	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().
		useStoryLoader(new LoadFromClasspath(this.getClass())).
		useStoryReporterBuilder(new StoryReporterBuilder()
			.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
			.withDefaultFormats()
			.withFormats(Format.HTML, Format.CONSOLE)
			.withFailureTrace(true)
		);
	}
	
//	public List<CandidateSteps> candidateSteps() {
//		List<CandidateSteps> candidateSteps = new InstanceStepsFactory(configuration(), 
//				new CadastroSelecaoSteps()).createCandidateSteps();
//		return candidateSteps;
//	}

}
