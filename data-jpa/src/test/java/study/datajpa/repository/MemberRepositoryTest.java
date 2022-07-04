package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member member = new Member("memberA", 10);
        Member member2 = new Member("memberA", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("memberA", 15);

        Assertions.assertThat(result.get(0).getUsername()).isEqualTo("memberA");
        Assertions.assertThat(result.get(0).getAge()).isEqualTo(20);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testQuery() {
        Member member = new Member("memberA", 10);
        Member member2 = new Member("memberA", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findUser("memberA", 10);

        Assertions.assertThat(result.get(0).getUsername()).isEqualTo("memberA");
        Assertions.assertThat(result.get(0).getAge()).isEqualTo(10);
    }

    @Test
    public void testFindUserName() {
        Member member = new Member("memberA", 10);
        Member member2 = new Member("memberA", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<String> result = memberRepository.findUserNameList();
        System.out.println(result);
    }

    @Test
    public void findMemberDto() {


        Member member = new Member("memberA", 10);
        Member member2 = new Member("memberA", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("memberA", ""));
        System.out.println(result);


    }

    @Test
    public void testPaging() {

        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        int age = 10;
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        // dto 변환
        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        List<Member> content = page.getContent();
        System.out.println(content);
        long totalElements = page.getTotalElements();
        System.out.println(totalElements);



    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 20));
        memberRepository.save(new Member("member3", 30));
        memberRepository.save(new Member("member4", 40));
        memberRepository.save(new Member("member5", 50));
        memberRepository.save(new Member("member6", 60));

        int resultCount = memberRepository.bulkAgePlus(20);



    }


}