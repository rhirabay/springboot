package rhirabay.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import rhirabay.db.UserRepository;

@Slf4j
@RequiredArgsConstructor
public class HelloWorldTasklet implements Tasklet {
    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Hello world");
        var user = userRepository.findById("1");
        log.info("user: {}", user);
        
        return RepeatStatus.FINISHED;
    }
}
