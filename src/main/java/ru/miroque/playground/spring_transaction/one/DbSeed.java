package ru.miroque.playground.spring_transaction.one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
//@RequiredArgsConstructor
public class DbSeed implements CommandLineRunner {
    @Autowired
    private RepoMainA repoMainA;
    @Autowired
    private RepoSubB repoSubB;
    @Autowired
    private ServiceFoo serviceFoo;

    @Override
    public void run(String... args) throws Exception {
        log.info("<-- Started -->");
        initA();
        initB();
        log.info("<-- Ended -->");
        log.info("<-- Play the GAME -->");
        play();
        log.info("<-- Stop the GAME -->");

    }

    private void play() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        serviceFoo.sampleOne();
        pool.submit(()-> serviceFoo.sampleSumupA());
        pool.submit(()-> serviceFoo.addNewA());
        pool.submit(()-> serviceFoo.sampleSumupA());

        pool.shutdown();
        serviceFoo.samplePrintB();
        serviceFoo.samplePrintB();
//        serviceFoo.samplePrintB();
//        service.awaitTermination(1000, TimeUnit.MILLISECONDS);


    }

    private void initA() {
        repoMainA.save(new MainA("one"));
        repoMainA.save(new MainA("two"));
        repoMainA.save(new MainA("tree"));
    }

    private void initB() {
        var one = new SubB();
        one.setMain(repoMainA.findById(1).get());
        one.setName("one to A-one");
        repoSubB.save(one);

        var two = new SubB();
        two.setMain(repoMainA.findById(1).get());
        two.setName("two to A-one");
        repoSubB.save(two);

        var three = new SubB();
        three.setMain(repoMainA.findById(1).get());
        three.setName("three to A-one");
        repoSubB.save(three);

        var odin = new SubB();
        odin.setMain(repoMainA.findById(2).get());
        odin.setName("odin to A-two");
        repoSubB.save(odin);

    }
}
