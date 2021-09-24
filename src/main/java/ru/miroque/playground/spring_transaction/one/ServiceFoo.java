package ru.miroque.playground.spring_transaction.one;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceFoo {
    private final RepoMainA repoMainA;
    private final RepoSubB repoSubB;

    public void sampleOne(){
        log.info("   - service-foo v one");
        repoMainA.findAll();
        repoSubB.findAll();
        log.info("   - service-foo ^ one");
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void sampleSumupA(){
        log.info("   - service-foo v sumup of A");
        var i = repoMainA.findAll().size();
        var sum  = new SubB();
        sum.setName("sumup action:: " +String.valueOf(i));
        repoSubB.save(sum);
        log.info("   - service-foo ^ sumup of A");
    }

    public void samplePrintB(){
        log.info("   - service-foo v print b");
        repoSubB.findAll().forEach(System.out::println);
        log.info("   - service-foo ^ print b");
    }

    public void addNewA() {
        log.info("   - service-foo v add new A");
        repoMainA.save(new MainA("serv ::"));
    }
}
