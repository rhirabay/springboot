package rhirabay.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class BankConfiguration {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;
    
    @Bean
    public HelloWorldTasklet helloWorldTasklet() {
        return new HelloWorldTasklet();
    }

    @Bean
    public Step helloWorldStep() {
        return stepBuilderFactory.get("helloWorldStep")
                .tasklet(helloWorldTasklet())
                .build();
    }

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .incrementer(new RunIdIncrementer())
                .flow(helloWorldStep())
                .end()
                .build();
    }

    public class HelloWorldTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            System.out.println("Hello world");

            return RepeatStatus.FINISHED;
        }
    }
}
