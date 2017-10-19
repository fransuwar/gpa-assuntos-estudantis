package br.ufc.npi.auxilio.test;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.ufc.npi.auxilio.AuxilioMoradiaApplication;
import br.ufc.npi.auxilio.test.pages.CadastroSteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { AuxilioMoradiaApplication.class})
public class CadastrarSelecaoTest extends JUnitStories{

    public CadastrarSelecaoTest() {
        JUnitReportingRunner.recommandedControls(configuredEmbedder());
     }

    public Configuration configuration() {
    	Class<?> thisClass = this.getClass();
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(thisClass.getClassLoader()))
                .useStepdocReporter(new PrintStreamStepdocReporter())
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(thisClass))
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML)
                        .withCrossReference(new CrossReference())
                        .withFailureTrace(true))
                .useParameterConverters(new ParameterConverters(null)
                        .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd"))))
                .useStepMonitor(new SilentStepMonitor());
    }

    @Override

    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new CadastroSteps());
    }

    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", null);
    }
	
//	private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
//    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
//    //private PageFactory pages = new PageFactory(driverProvider);
//    private SeleniumContext context = new SeleniumContext();
//    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
//	
//    public CadastrarSelecaoTest() {
//    	 if ( lifecycleSteps instanceof PerStoriesWebDriverSteps ){
//             configuredEmbedder().useExecutorService(new SameThreadExecutors().create(configuredEmbedder().embedderControls()));
//         }
//    }
//    
//	public Configuration configuration() {
//		Class<? extends Embeddable> embeddableClass = this.getClass();
//        return new SeleniumConfiguration()
//                .useSeleniumContext(context)
//                .useWebDriverProvider(driverProvider)
//                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
//                .useStoryLoader(new LoadFromClasspath(embeddableClass))
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                    .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
//                    .withDefaultFormats()
//                    .withFormats(Format.CONSOLE, Format.HTML));
//    }
//
//	 @Override
//	    public InjectableStepsFactory stepsFactory() {
//	        Configuration configuration = configuration();
//	        return new InstanceStepsFactory(configuration, 
//	                new CadastroSteps(),
//	                lifecycleSteps,
//	                new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
//	    }
//
//
//    @Override
//    protected List<String> storyPaths() {
//        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
//    }
	
}
