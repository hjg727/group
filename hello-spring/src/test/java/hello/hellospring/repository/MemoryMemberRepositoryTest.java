package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//콜백 메소드=어떠한 동작이 끝날때마다 이 메소드 실행
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);변수 순서 헷갈린다. result, member일수도있다.
        //Assertions.assertThat(member).isEqualTo(result);->org.assertj.core.api꺼다. 이게 더 잘 읽힌다.
        assertThat(member).isEqualTo(result);//alt+enter키누르니까 위에 import해준다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //member1은 spring1이라는 이름의 회원, member2는 spring2라는 이름의 회원

        Member result = repository.findByName("spring1").get();
        //get없으면 Optional<Memeber> result여야한다.

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //결과로받은 List형태의 result의 size는 회원들의 수를 의미한다.

    }

}
