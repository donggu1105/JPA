package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import javax.persistence.Id;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
