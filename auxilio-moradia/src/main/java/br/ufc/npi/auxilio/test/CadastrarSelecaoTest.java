package br.ufc.npi.auxilio.test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory; 
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.ufc.npi.auxilio.test.steps.CadastroSelecaoSteps;
import br.ufc.npi.auxilio.test.steps.DetalhesDoAlunoSteps;

@RunWith(SpringJUnit4ClassRunner.class)
public class CadastrarSelecaoTest extends JUnitStories{

    public CadastrarSelecaoTest() {
    	configuredEmbedder().embedderControls() 
        					.doGenerateViewAfterStories(true) 
        					.doIgnoreFailureInStories(false)
        					.doIgnoreFailureInView(false); 
    	//JUnitReportingRunner.recommandedControls(configuredEmbedder());
     }

    public Configuration configuration() {
    	Class<? extends Embeddable> embeddableClass = this.getClass(); 
    	
//    	Keywords keywords = new LocalizedKeywords(new Locale("pt", "BR"));
    	LoadFromClasspath loadFromClasspath = new LoadFromClasspath(embeddableClass);
//    	ParameterConverters parameterConverters = new ParameterConverters(null);
//    	
//    	ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
//    			keywords,
//    			loadFromClasspath, 
//    			parameterConverters, 
//    			null);
//    	
//    	parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("dd-MM-yyyy")), 
//    			new ExamplesTableConverter(examplesTableFactory));
    	
    	return new MostUsefulConfiguration()
//                .useKeywords(keywords)
//                .useStoryParser(new RegexStoryParser(examplesTableFactory))
        		.useStoryLoader(loadFromClasspath)
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML)
                        .withFailureTrace(true))
                .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
//                .useParameterConverters(parameterConverters)
                .useStepMonitor(new SilentStepMonitor());
    }

    @Override

    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new DetalhesDoAlunoSteps());
    }

    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", null);
    }

}
